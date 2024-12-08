package com.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.demo")
public class MvcExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MvcExampleApplication.class, args);
    }


}
