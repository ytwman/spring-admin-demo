package com.example.springcachemanager;

import com.example.consumer.aliyunons.listener.MessageEventPublisher;
import com.example.springcachemanager.messagelistener.OrderEvent;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author hank (hank@meiyibao.com)
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class HelloServiceTest {

    @Resource
    private MessageEventPublisher messageEventPublisher;

    @Before
    public void init() {
        System.setProperty("spring.rocketmq.enabled", "true");
    }

    @org.junit.Test
    public void sayHello() {
        OrderEvent messageEvent = new OrderEvent();
        messageEvent.setOrderId(123456789l);
        messageEventPublisher.publishEvent(messageEvent, null);
    }
}