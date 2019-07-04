package com.example.springcachemanager;

import com.example.consumer.aliyunons.listener.MessageEventPublisher;
import com.example.springcachemanager.messagelistener.OrderEvent;
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

    public void orderMessage(int status) {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId(123l);
        orderEvent.setKey("123");
        MessageEventPublisher.publishEvent(orderEvent, Integer.toString(status));
    }
}
