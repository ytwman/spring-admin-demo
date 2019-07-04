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
import org.springframework.context.annotation.Import;

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author hank (hank@meiyibao.com)
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "spring.rocketmq.enabled", havingValue = "true")
@Import(ConsumerScannerRegistrar.class)
@EnableConfigurationProperties(RocketMQClientConfigBean.class)
public class RocketMQClientAutoConfiguration {

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Producer getProducer(RocketMQClientConfigBean config) {
        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(asProperties(config));
        return producerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public OrderProducer getOrderProducer(RocketMQClientConfigBean config) {
        OrderProducerBean producerBean = new OrderProducerBean();
        producerBean.setProperties(asProperties(config));
        return producerBean;
    }

    //    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public TransactionProducer getTransactionProducer(RocketMQClientConfigBean config) {
        TransactionProducerBean producerBean = new TransactionProducerBean();
        producerBean.setProperties(asProperties(config));
//        producerBean.setLocalTransactionChecker();

        return producerBean;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdown")
    public Consumer getConsumer(RocketMQClientConfigBean config, ConsumerScannerRegistrar registrar) {
        ConsumerBean bean = new ConsumerBean();
        bean.setProperties(asProperties(config));
        // 指定客户端的 groupid, 拼装环境区分后缀
        String gid = config.getClient().getGroupId();
        Objects.requireNonNull(gid, "spring.rocketmq.client.group-id 不能为空");
        String groupId = Objects.nonNull(config.getGroupSuffix())
                ? gid.concat(config.getGroupSuffix())
                : (Objects.nonNull(config.getEnvSuffix()) ? gid.concat(config.getEnvSuffix()) : config.getClient().getGroupId());
        bean.getProperties().putIfAbsent(PropertyKeyConst.GROUP_ID, groupId);

        if (config.getClient().getConsumeThreadNums() != null) {
            bean.getProperties().putIfAbsent(PropertyKeyConst.ConsumeThreadNums, config.getClient().getConsumeThreadNums());
        }
        Map<Subscription, MessageListener> subscriptionTable = registrar.getSubscriptionTable(config);
        if (subscriptionTable.isEmpty()) {
            log.warn("client GroupId:{}, not found message listener.", config.getClient().getGroupId());
            return null;
        }

        bean.setSubscriptionTable(subscriptionTable);

        return bean;
    }

    private Properties asProperties(RocketMQClientConfigBean config) {
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
