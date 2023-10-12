package com.harry;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.harry.mapper")
@EnableScheduling
@EnableSwagger2
public class HarryBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarryBlogApplication.class,args);
    }

}
