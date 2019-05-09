package com.att;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.att")
public class ConfigurationApp {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationApp.class, args);
    }
}
