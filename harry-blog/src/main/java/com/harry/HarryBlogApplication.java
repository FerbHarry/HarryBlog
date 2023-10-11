package com.harry;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.harry.mapper")
@EnableScheduling
public class HarryBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarryBlogApplication.class,args);
    }

}
