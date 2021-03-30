package com.usc.brainattacker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.usc.brainattacker.mapper")
public class BrainAttackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrainAttackerApplication.class, args);
    }

}
