package com.utc.todo.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(value = "com.utc.todo.entity")
@EnableJpaRepositories(value = "com.utc.todo.repository")
@ComponentScan(value = "com.utc.todo")
public class TodoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }
}
