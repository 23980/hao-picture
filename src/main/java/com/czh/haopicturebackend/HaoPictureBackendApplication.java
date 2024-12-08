package com.czh.haopicturebackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.czh.haopicturebackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class HaoPictureBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaoPictureBackendApplication.class, args);
    }

}