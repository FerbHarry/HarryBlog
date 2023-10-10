package com.harry;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.harry.mapper")
public class HarryBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarryBlogApplication.class,args);
    }

}
