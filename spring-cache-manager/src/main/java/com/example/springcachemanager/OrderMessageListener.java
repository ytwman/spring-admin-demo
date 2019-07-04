package com.example.springcachemanager;

import com.example.consumer.aliyunons.listener.MessageListener;

/**
 * @author hank (hank@meiyibao.com)
 */
public class OrderMessageListener implements MessageListener<OrderEvent> {
    @Override
    public void consume(OrderEvent event) {
        System.out.println(event + "-------------------------");
    }
}
