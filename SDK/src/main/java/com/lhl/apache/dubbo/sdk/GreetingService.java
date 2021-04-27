package com.lhl.apache.dubbo.sdk;

/**
 * 重要用于演示同步调用
 * @author lvhonglei
 */
public interface GreetingService {

    String sayHello(String name);

    Result<String> testGeneric(PoJo poJo);
}
