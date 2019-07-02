package com.example.springcachemanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author hank (hank@meiyibao.com)
 */
@Slf4j
@Service
public class HelloService {

    @Cacheable("cache1")
    public String sayHello(String name) {

        if (log.isDebugEnabled()) {
            log.debug("未进入 cache...");
        }

        log.info("not in cache...");

        return "hello, " + name;
    }
}
