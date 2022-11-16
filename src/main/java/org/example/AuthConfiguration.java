package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetails() {
        UserDetails userDetails1 = User.withUsername("Test1").password("{noop}Test1").roles("user").build();
        UserDetails userDetails2 = User.withUsername("Test2").password("{noop}Test2").roles("user").build();
        UserDetails userDetails3 = User.withUsername("Test3").password("{noop}Test3").roles("user").build();
        return new InMemoryUserDetailsManager(Arrays.asList(userDetails1, userDetails2, userDetails3));
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
        return httpSecurity.build();
    }

}
