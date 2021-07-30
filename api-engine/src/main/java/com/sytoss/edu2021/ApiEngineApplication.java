package com.sytoss.edu2021;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
@EnableFeignClients
public class ApiEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiEngineApplication.class, args);
    }
}
