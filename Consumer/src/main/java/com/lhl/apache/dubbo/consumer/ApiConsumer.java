package com.lhl.apache.dubbo.consumer;

import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 同步调用服务
 * @author lvhonglei
 */
public class ApiConsumer {

    public static void main(String[] args) throws InterruptedException {
        //9)创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<GreetingService>();
        //10)设置应用程序信息
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        //11)设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        //12)设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);
        //13)设置自定义负载均衡策略与集群容错策略
        referenceConfig.setLoadbalance("myLoadBalance");
        referenceConfig.setCluster("myBroadcast");
        //14)设置服务分组与版本
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        //15)引用服务
        GreetingService greetingService = referenceConfig.get();
        //16)设置隐式参数
        RpcContext.getContext().setAttachment("company","alibaba");
        //17)调用服务
        System.out.println(greetingService.sayHello("world"));

        Thread.currentThread().join();
    }

}
