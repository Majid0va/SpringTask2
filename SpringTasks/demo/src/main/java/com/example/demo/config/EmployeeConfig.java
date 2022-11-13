package com.example.demo.config;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.EmployeeRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class EmployeeConfig {

    @Bean
    public EmployeeRepository employeeRepository() {
        return new EmployeeRepositoryImpl();
    }
}
