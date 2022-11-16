package org.example.filter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserFilter implements Filter {

    private static Map<Principal, Bucket> principalAPICountMap;
    private static final Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));

    static {
        principalAPICountMap = new ConcurrentHashMap();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) request).getRequestURI().contains("login")) {
            chain.doFilter(request, response);
            return;
        }

        Principal principal = ((SecurityContextHolderAwareRequestWrapper) request).getUserPrincipal();
        Bucket bucket;
        if (principalAPICountMap.containsKey(principal)) {
            bucket = principalAPICountMap.get(principal);
        } else {
            bucket = Bucket4j.builder().addLimit(limit).build();
            principalAPICountMap.put(principal, bucket);
        }
        if (consumeBucket(bucket, response))
            chain.doFilter(request, response);
        else
            return;
    }

    public boolean consumeBucket(Bucket bucket, ServletResponse response) throws IOException {
        if (!bucket.tryConsume(1)) {
            ((HttpServletResponse) response).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getOutputStream().write(new String("Too many requests").getBytes(StandardCharsets.UTF_8));
            return false;
        }
        return true;
    }

}
