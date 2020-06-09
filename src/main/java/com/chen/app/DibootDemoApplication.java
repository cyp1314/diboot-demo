package com.chen.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@EnableTransactionManagement
@SpringBootApplication
public class DibootDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DibootDemoApplication.class, args);
    }

}
