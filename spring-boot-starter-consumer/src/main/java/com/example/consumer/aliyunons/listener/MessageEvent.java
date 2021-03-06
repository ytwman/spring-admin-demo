package com.example.consumer.aliyunons.listener;

import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;

/**
 * @author hank (hank@meiyibao.com)
 */
public class MessageEvent {

    /**
     * 业务主键
     */
    private String key;

    private Message message;

    private ConsumeContext consumeContext;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public ConsumeContext getConsumeContext() {
        return consumeContext;
    }

    public void setConsumeContext(ConsumeContext consumeContext) {
        this.consumeContext = consumeContext;
    }
}
