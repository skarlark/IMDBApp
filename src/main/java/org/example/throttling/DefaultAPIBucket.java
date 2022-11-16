package org.example.throttling;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;

import java.net.HttpRetryException;
import java.time.Duration;

public abstract class DefaultAPIBucket implements APIBucket {

    private final Bucket bucket;

    DefaultAPIBucket() {
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    public void consumeBucket() throws HttpRetryException {
        /*
        if (!bucket.tryConsume(1))
            throw new HttpRetryException("Too many requests. Rate limit exceeded.", 429);

         */
    }
}
