package com.lhl.apache.dubbo.sdk;

import java.util.concurrent.CompletableFuture;

/**
 * 演示基于CompletableFuture签名的异步实现
 * @author lvhonglei
 */
public interface GreetingServiceAsync {

    CompletableFuture<String> sayHello(String name);
}
