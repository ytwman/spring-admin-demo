package com.example.springcachemanager;

import com.example.consumer.aliyunons.annotation.EnableRocketMQClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author hank (hank@meiyibao.com)
 */
@EnableCaching
@EnableEurekaClient
@EnableRocketMQClient
@SpringBootApplication
public class SpringCacheManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCacheManagerApplication.class, args);
    }
}
