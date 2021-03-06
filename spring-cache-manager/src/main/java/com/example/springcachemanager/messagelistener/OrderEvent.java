package com.example.springcachemanager.messagelistener;

import com.example.consumer.aliyunons.annotation.Topic;
import com.example.consumer.aliyunons.listener.MessageEvent;
import lombok.ToString;

/**
 * @author hank (hank@meiyibao.com)
 */
@ToString
@Topic("TID_SHOP")
public class OrderEvent extends MessageEvent {
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
