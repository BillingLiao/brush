package com.shokey.brushadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shokey.brushdao.mapper")
public class BrushAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrushAdminApplication.class, args);
    }
}
