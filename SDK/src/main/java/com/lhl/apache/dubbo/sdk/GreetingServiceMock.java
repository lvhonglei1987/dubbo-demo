package com.lhl.apache.dubbo.sdk;

import com.lhl.apache.dubbo.sdk.GreetingService;
import com.lhl.apache.dubbo.sdk.PoJo;
import com.lhl.apache.dubbo.sdk.Result;

/**
 *
 */
public class GreetingServiceMock implements GreetingService {

    @Override
    public String sayHello(String name) {
        return "mock value";
    }

    @Override
    public Result<String> testGeneric(PoJo poJo) {
        return null;
    }
}
