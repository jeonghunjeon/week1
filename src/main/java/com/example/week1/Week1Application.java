package com.example.week1;

import com.example.week1.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week1Application {

    public static void main(String[] args) {
        SpringApplication.run(Week1Application.class, args);
    }

    @Autowired
    private BlogRepository blogRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> {
        };
    }
}
