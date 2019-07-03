package com.example.consumer.listener;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author hank (hank@meiyibao.com)
 */
@Configuration
public class MessageEventPublisher {

    @Resource
    private Producer producer;

    @Resource
    private OrderProducer orderProducer;

    /**
     *
     * @param message
     * @param shardingKey
     * @return
     */
    public SendResult publishEvent(Message message, final String shardingKey) {
        return orderProducer.send(message, shardingKey);
    }

    public SendResult publishEvent(Message message) {
        return producer.send(message);
    }
}
