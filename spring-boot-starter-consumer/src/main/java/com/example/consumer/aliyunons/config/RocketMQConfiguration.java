package com.example.consumer.aliyunons.config;

import com.aliyun.openservices.ons.api.*;
import com.aliyun.openservices.ons.api.bean.*;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import com.aliyun.openservices.ons.api.transaction.TransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author hank (hank@meiyibao.com)
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "spring.rocketmq.enabled", havingValue = "true")
@EnableConfigurationProperties(RocketMQConfig.class)
public class RocketMQConfiguration {

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Producer getProducer(RocketMQConfig config) {
        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(asProperties(config));
        return producerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducer getOrderProducer(RocketMQConfig config) {
        OrderProducerBean producerBean = new OrderProducerBean();
        producerBean.setProperties(asProperties(config));
        return producerBean;
    }

//    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducer getTransactionProducer(RocketMQConfig config) {
        TransactionProducerBean producerBean = new TransactionProducerBean();
        producerBean.setProperties(asProperties(config));
//        producerBean.setLocalTransactionChecker();

        return producerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer getConsumer(RocketMQConfig config, RocketMQSubscription subscription) {
        ConsumerBean bean = new ConsumerBean();
        bean.setProperties(asProperties(config));
        // 指定客户端的 groupid, 拼装环境区分后缀
        String groupId = Objects.nonNull(config.getGroupSuffix())
                ? config.getClient().getGroupId().join(config.getGroupSuffix())
                : (Objects.nonNull(config.getEnvSuffix())
                    ? config.getClient().getGroupId().join(config.getEnvSuffix()) : config.getClient().getGroupId());
        bean.getProperties().putIfAbsent(PropertyKeyConst.GROUP_ID, groupId);

        if (config.getClient().getConsumeThreadNums() != null) {
            bean.getProperties().putIfAbsent(PropertyKeyConst.ConsumeThreadNums, config.getClient().getConsumeThreadNums());
        }
        Map<Subscription, MessageListener> subscriptionTable = subscription.getSubscriptionTable(config);
        if (!subscriptionTable.isEmpty()) {
            log.warn("client GroupId:{}, not found message listener.", config.getClient().getGroupId());
            bean.setSubscriptionTable(subscriptionTable);
        }
        return bean;
    }

    private Properties asProperties(RocketMQConfig config) {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SecretKey, config.getAccessKeySecret());
        properties.put(PropertyKeyConst.AccessKey, config.getAccessKeyId());
        if (Objects.nonNull(config.getNamesrvAddr())) {
            properties.putIfAbsent(PropertyKeyConst.NAMESRV_ADDR, config.getNamesrvAddr());
        }
        properties.put(PropertyKeyConst.MessageModel, PropertyValueConst.CLUSTERING);
        return properties;
    }
}
