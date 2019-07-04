package com.example.consumer.aliyunons.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author hank (hank@meiyibao.com)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Consumer {
    /**
     * tag 默认不过滤
     */
    @AliasFor("tag")
    String value() default "*";

    @AliasFor("value")
    String tag() default "*";
}
