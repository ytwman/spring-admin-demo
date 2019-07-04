package com.example.springcachemanager.messagelistener;

import com.example.consumer.aliyunons.annotation.Consumer;
import com.example.consumer.aliyunons.listener.MessageListener;

/**
 * @author hank (hank@meiyibao.com)
 */
@Consumer("1")
public class OrderMessageListener implements MessageListener<OrderEvent> {
    @Override
    public void consume(OrderEvent event) {
        System.out.println(event + "-------------------------");
    }
}
