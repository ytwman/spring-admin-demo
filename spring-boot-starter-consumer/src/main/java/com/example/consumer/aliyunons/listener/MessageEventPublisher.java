package com.example.consumer.aliyunons.listener;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.example.consumer.aliyunons.annotation.Topic;
import com.example.consumer.aliyunons.config.RocketMQConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author hank (hank@meiyibao.com)
 */
@Configuration
public class MessageEventPublisher {

    @Resource
    private Producer producer;

    @Resource
    private OrderProducer orderProducer;

    @Resource
    private RocketMQConfig config;

    private static MessageEventPublisher messageEventPublisher;

    @PostConstruct
    public void init() {
        messageEventPublisher = this;
    }

    public Producer getProducer() {
        return producer;
    }

    public OrderProducer getOrderProducer() {
        return orderProducer;
    }

    public RocketMQConfig getConfig() {
        return config;
    }

    /**
     * 顺序发送消息
     *
     * @param event
     * @param shardingKey
     * @return 发送结果
     */
    public static <E extends MessageEvent> SendResult publishEvent(final E event, String tags, final String shardingKey) {
        return messageEventPublisher.getOrderProducer().send(buildMessage(event, tags), shardingKey);
    }

    public static <E extends MessageEvent> SendResult publishEvent(E event, String tags) {
        return messageEventPublisher.getProducer().send(buildMessage(event, tags));
    }

    private static <E extends MessageEvent> Message buildMessage(final E event, String tags) {
        Topic topic = event.getClass().getAnnotation(Topic.class);
        Assert.notNull(topic, String.format("message event: {}, not topic specified.", event.getClass().getName()));

        RocketMQConfig config = messageEventPublisher.getConfig();

        // 拼装环境区分后缀
        String topicName = Objects.nonNull(config.getTopicSuffix())
                ? topic.value().concat(config.getTopicSuffix())
                : (Objects.nonNull(config.getEnvSuffix()) ? topic.value().concat(config.getEnvSuffix()) : topic.value());

        Message message = new Message();
        message.setTopic(topicName);
        message.setTag(tags);
        message.setKey(event.getKey());
        message.setBody(JSON.toJSONString(event).getBytes());
//        message.setStartDeliverTime();
//        message.setBornTimestamp();

        return message;
    }
}
