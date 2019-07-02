package com.example.springadminserver;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hank (hank@meiyibao.com)
 */
@SpringBootApplication
@EnableAdminServer
@EnableDiscoveryClient
public class SpringAdminServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAdminServerApplication.class, args);
    }
}
