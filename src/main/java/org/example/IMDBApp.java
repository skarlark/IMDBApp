package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TODO remove unused/mass import, logging, auth, throttling facade, test cases
@SpringBootApplication
public class IMDBApp {
    public static void main(String[] args) {
        SpringApplication.run(IMDBApp.class, args);
    }
}