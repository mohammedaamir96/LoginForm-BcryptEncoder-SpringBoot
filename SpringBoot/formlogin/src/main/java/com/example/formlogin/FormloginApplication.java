package com.example.formlogin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@ComponentScan(basePackages = {"com.example.formlogin", "com.example.formlogin.repository", "com.example.formlogin.service"})
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.formlogin") // Add this line
public class FormloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormloginApplication.class, args);
    }
}
