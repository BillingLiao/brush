package com.shokey.brushadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.shokey.brushdao.mapper")
@EnableTransactionManagement
public class BrushAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrushAdminApplication.class, args);
    }
}
