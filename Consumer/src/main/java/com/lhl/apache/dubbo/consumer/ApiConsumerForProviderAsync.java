package com.lhl.apache.dubbo.consumer;

import com.lhl.apache.dubbo.sdk.GreetingServiceAsync;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

/**
 * 异步调用方法并返回CompletableFuture,并设置回调
 */
public class ApiConsumerForProviderAsync {

    public static void main(String[] args) throws InterruptedException {
        //1)创建服务引用实例，并设置
        ReferenceConfig<GreetingServiceAsync> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingServiceAsync.class);
        referenceConfig.setTimeout(5000);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //2)服务引用
        GreetingServiceAsync greetingServiceAsync = referenceConfig.get();

        //3)设置隐式参数
        RpcContext.getContext().setAttachment("company","lhl");

        //4)获取Future并设置回调
        CompletableFuture<String> future = greetingServiceAsync.sayHello("World");
        future.whenComplete((v,t) -> {
            if (t != null){
                t.printStackTrace();
            }else {
                System.out.println(v);
            }
        });

        Thread.currentThread().join();
    }

}
