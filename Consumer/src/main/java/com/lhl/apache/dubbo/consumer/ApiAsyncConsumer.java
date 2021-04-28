package com.lhl.apache.dubbo.consumer;

import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步调用,利用Future实现
 * 缺点，在调用get()方法后，业务会阻塞
 */
public class ApiAsyncConsumer {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1)创建引用实例，并设置属性
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        //添加超时时间，否则异常
        referenceConfig.setTimeout(5000);

        //2)设置为异步
        referenceConfig.setAsync(Boolean.TRUE);

        //3)直接返回null
        GreetingService greetingService = referenceConfig.get();
        System.out.println(greetingService.sayHello("world"));

        //4)等待结果
        Future<String> future = RpcContext.getContext().getFuture();
        System.out.println(future.get());
    }
}
