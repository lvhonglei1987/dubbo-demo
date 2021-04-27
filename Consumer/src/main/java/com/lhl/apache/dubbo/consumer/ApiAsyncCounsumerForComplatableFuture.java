package com.lhl.apache.dubbo.consumer;

import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

import java.util.concurrent.CompletableFuture;

/**
 * dubbo2.7版本提供的异步调用
 *
 * @author lvhonglei
 */
public class ApiAsyncCounsumerForComplatableFuture {

    public static void main(String[] args) throws InterruptedException {
        //1)创建服务引用对象，并设置数据
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(30000);
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //2)设置为异步
        referenceConfig.setAsync(Boolean.TRUE);

        //3)直接返回null
        CompletableFuture<String> future = RpcContext.getContext().getCompletableFuture();
        future.whenComplete((v,t) -> {
            if (t != null){
                t.printStackTrace();
            }else {
                System.out.println(v);
            }
        });
        System.out.println("over");
        Thread.currentThread().join();
    }

}
