package com.walle.cplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.walle.cplatform"})
@MapperScan(basePackages = {"com.walle.cplatform.mapper", "com.walle.cplatform.*.mapper"})
public class WalleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalleApplication.class, args);
    }
}
