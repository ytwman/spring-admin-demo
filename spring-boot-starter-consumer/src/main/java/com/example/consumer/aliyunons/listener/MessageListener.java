package com.example.consumer.aliyunons.listener;

import java.util.EventListener;

/**
 * @author hank (hank@meiyibao.com)
 */
@FunctionalInterface
public interface MessageListener<E extends MessageEvent> extends EventListener {

    void consume(E event);
}
