package com.example.consumer.aliyunons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hank (hank@meiyibao.com)
 */
@ConfigurationProperties("spring.rocketmq")
public class RocketMQConfig {

    private String namesrvAddr;

    private String accessKeyId;

    private String accessKeySecret;

    private Client client;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public class Client {
        private String groupId;
        private Integer consumeThreadNums;

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public Integer getConsumeThreadNums() {
            return consumeThreadNums;
        }

        public void setConsumeThreadNums(Integer consumeThreadNums) {
            this.consumeThreadNums = consumeThreadNums;
        }
    }
}
