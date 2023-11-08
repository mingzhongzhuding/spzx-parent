package com.pingchang.spzx.manager;

import com.pingchang.spzx.manager.properties.MinioProperties;
import com.pingchang.spzx.manager.properties.UserProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: 命祌紸顁
 * @Date: 2023/10/18  15:58
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.pingchang.spzx")
@EnableConfigurationProperties(value = {UserProperties.class, MinioProperties.class})
public class ManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }
}
