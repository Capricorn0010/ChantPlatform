package com.github.chant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.github.chant.dao"})
public class ChantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChantApplication.class, args);
    }
}
