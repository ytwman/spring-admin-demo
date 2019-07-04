package com.example.consumer.aliyunons.config;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.aliyun.openservices.shade.com.alibaba.rocketmq.common.filter.ExpressionType;
import com.example.consumer.aliyunons.annotation.Consumer;
import com.example.consumer.aliyunons.annotation.Topic;
import com.example.consumer.aliyunons.listener.MessageEvent;
import com.example.consumer.aliyunons.listener.MessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author hank (hank@meiyibao.com)
 */
@Slf4j
@Configuration
public class ConsumerScannerRegistrar implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Map<Subscription, com.aliyun.openservices.ons.api.MessageListener> getSubscriptionTable(RocketMQClientConfigBean config) {
        Map<Subscription, com.aliyun.openservices.ons.api.MessageListener> subscriptions = new HashMap<>();

        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(Consumer.class);

        for (Object bean : beans.values()) {
            if (bean instanceof MessageListener) {
                // 获取 message listener
                final MessageListener listener = (MessageListener) bean;
                Consumer consumer = listener.getClass().getAnnotation(Consumer.class);
                Assert.notNull(consumer, String.format("message listener: {}, not consumer specified.", listener.getClass().getName()));

                // 获取泛型上的 topic 注解
                final Class<?> eventClass = GenericTypeResolver.resolveTypeArgument(listener.getClass(), MessageListener.class);
                Topic topic = eventClass.getClass().getAnnotation(Topic.class);
                Assert.notNull(topic, String.format("message listener: {}, not topic specified.", listener.getClass().getName()));

                com.aliyun.openservices.ons.api.MessageListener messageListener = (message, context) -> {
                    try {
                        log.info("message listener: {}, topic: {}, tag:{}, msgid: {}, bizid: {},  receiver content: {}",
                                listener.getClass(), message.getTopic(), message.getTag(), message.getMsgID(), message.getKey(), message.getBody());
                        MessageEvent messageEvent = JSON.parseObject(message.getBody(), eventClass);
                        messageEvent.setMessage(message);
                        listener.consume(messageEvent);
                    } catch (Exception e) {
                        log.error("receiver message process failure, message id:{}, biz id: {}, topic: {}", message.getMsgID(), message.getKey(), topic.value(), e);
                        return Action.ReconsumeLater;
                    }
                    return Action.CommitMessage;
                };

                // 拼装环境区分后缀
                String topicName = Objects.nonNull(config.getTopicSuffix())
                        ? topic.value().concat(config.getTopicSuffix())
                        : (Objects.nonNull(config.getEnvSuffix()) ? topic.value().concat(config.getEnvSuffix()) : topic.value());

                subscriptions.put(asSubscription(consumer, topicName), messageListener);
                log.info("message listener: {}, subscription topic: {}, tag: {}.", listener.getClass().getName(), topic.value(), consumer.tag());
            }
        }

        return subscriptions;
    }

    private Subscription asSubscription(Consumer consumer, String topic) {
        Subscription subscription = new Subscription();
        subscription.setTopic(topic);
        subscription.setTopic(consumer.value().isEmpty() ? consumer.tag() : consumer.value());
        subscription.setType(ExpressionType.TAG);
        subscription.setExpression("*");
        return subscription;
    }
}
