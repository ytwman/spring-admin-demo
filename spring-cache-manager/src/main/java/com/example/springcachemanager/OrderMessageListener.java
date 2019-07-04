package com.example.springcachemanager;

import com.example.consumer.aliyunons.annotation.Consumer;
import com.example.consumer.aliyunons.listener.MessageListener;

/**
 * @author hank (hank@meiyibao.com)
 */
@Consumer
public class OrderMessageListener implements MessageListener<OrderEvent> {
    @Override
    public void consume(OrderEvent event) {
        System.out.println(event + "-------------------------");
    }
}
