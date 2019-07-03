package com.example.consumer;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hank (hank@meiyibao.com)
 */
@ConfigurationProperties("spring.rocketmq")
public class RocketMQConfig {

    private String groupId;

    private String namesrvAddr;

    private String accessKeyId;

    private String accessKeySecret;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

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
}
