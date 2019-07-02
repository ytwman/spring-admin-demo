package com.example.springadmineureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hank (hank@meiyibao.com)
 */
@EnableEurekaServer
@SpringBootApplication
public class SpringAdminEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAdminEurekaApplication.class, args);
    }
}
