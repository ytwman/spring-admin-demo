package com.example.consumer.aliyunons.annotation;

import com.example.consumer.aliyunons.config.RocketMQClientAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author hank (hank@meiyibao.com)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RocketMQClientAutoConfiguration.class)
public @interface EnableRocketMQClient {
}
