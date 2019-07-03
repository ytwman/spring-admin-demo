package com.example.consumer.annotation;

import java.lang.annotation.*;

/**
 * @author hank (hank@meiyibao.com)
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Topic {

    String value();
}
