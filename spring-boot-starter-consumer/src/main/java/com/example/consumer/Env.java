package com.example.consumer;

import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author hank (hank@meiyibao.com)
 */
@Component
public class Env {

    @Value("${spring.profiles.active}")
    private static String envActive;

    /**
     * 本地开发环境
     */
    public static final String LOCAL_ENV = "LOCAL";

    /**
     * 是否本地开发环境
     */
    public static boolean hasLocalEnv() {
        return (Strings.isNullOrEmpty(envActive) || envActive.equalsIgnoreCase(LOCAL_ENV));
    }
}
