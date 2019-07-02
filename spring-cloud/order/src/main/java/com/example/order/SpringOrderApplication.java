package com.example.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hank (hank@meiyibao.com)
 */
@EnableEurekaClient
@SpringBootApplication
public class SpringOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringOrderApplication.class, args);
    }
}
