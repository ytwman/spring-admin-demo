package com.example.springcachemanager;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author hank (hank@meiyibao.com)
 */
@Service
public class HelloService {

    @Cacheable("cache1")
    public String sayHello(String name) {
        return "hello, " + name;
    }
}
