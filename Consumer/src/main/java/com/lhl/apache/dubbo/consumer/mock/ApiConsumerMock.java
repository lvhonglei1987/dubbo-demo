package com.lhl.apache.dubbo.consumer.mock;

import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

/**
 * mock类
 * 本地化测试用
 */
public class ApiConsumerMock {

    public static void main(String[] args){
        //0)创建服务引用对象实例
        ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<>();
        //1)设置程序应用信息
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        //2)设置服务注册中心
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        //3)设置服务接口和超时时间
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setTimeout(5000);
        //4)设置服务版本和分组
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");
        //5)设置启动的时候不检查服务是否启动
        referenceConfig.setCheck(false);
        referenceConfig.setMock(true);
        //6)引用服务
        GreetingService greetingService = referenceConfig.get();
        //7)设置隐式参数
        RpcContext.getContext().setAttachment("company","lhl");
        //8)调用服务
        String world = greetingService.sayHello("world");
        System.out.println(world);
    }
}
