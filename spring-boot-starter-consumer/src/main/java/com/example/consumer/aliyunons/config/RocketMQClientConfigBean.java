package com.example.consumer.aliyunons.config;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.client.log.ClientLogger;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hank (hank@meiyibao.com)
 */
@ConfigurationProperties("spring.rocketmq")
public class RocketMQClientConfigBean {

    private String namesrvAddr;

    /**
     * aliyun 平台访问 key
     */
    private String accessKeyId;

    /**
     * 阿里云平台访问 秘钥
     */
    private String accessKeySecret;

    /**
     * topic 后缀，用于区分不同的环境后缀
     */
    private String topicSuffix;

    /**
     * group id 后缀，用于区分不同环境的后缀
     */
    private String groupSuffix;

    /**
     * 环境后缀
     */
    private String envSuffix;

    /**
     * 是否开启 mq
     */
    private String enabled = Boolean.TRUE.toString();

    /**
     * 客户端参数
     */
    private  Client client = new Client();

    // 配置日志
    static {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
        System.setProperty(ClientLogger.CLIENT_LOG_LEVEL, "WARN");
        System.setProperty("ons.client.logLevel", "WARN");
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

    public String getTopicSuffix() {
        return topicSuffix;
    }

    public void setTopicSuffix(String topicSuffix) {
        this.topicSuffix = topicSuffix;
    }

    public String getGroupSuffix() {
        return groupSuffix;
    }

    public void setGroupSuffix(String groupSuffix) {
        this.groupSuffix = groupSuffix;
    }

    public String getEnvSuffix() {
        return envSuffix;
    }

    public void setEnvSuffix(String envSuffix) {
        this.envSuffix = envSuffix;
    }

    public Client getClient() {
        return client;
    }

    public static class Client {
        /**
         * 客户端 id
         */
        private String groupId;
        /**
         * 消费线程数量
         */
        private Integer consumeThreadNums = 10;

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
