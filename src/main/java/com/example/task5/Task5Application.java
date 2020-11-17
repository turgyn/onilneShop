package com.example.task5;

import com.example.task5.repositories.AccountRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = AccountRepository.class)
public class Task5Application {

    public static void main(String[] args) {
        SpringApplication.run(Task5Application.class, args);
    }

}
