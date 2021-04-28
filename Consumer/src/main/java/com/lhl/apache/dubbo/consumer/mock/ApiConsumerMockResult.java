package com.lhl.apache.dubbo.consumer.mock;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.RegistryFactory;

/**
 * 服务降级
 * force:return 直接返回mock结果
 * fail:return 调用服务成功，返回正常结果；调用服务失败，返回mock结果
 * @author lvhonglei
 */
public class ApiConsumerMockResult {

    public static void mockResult(String type) {
        //1)获取服务注册中心工厂
        RegistryFactory registryFactory = ExtensionLoader
                .getExtensionLoader(RegistryFactory.class)
                .getAdaptiveExtension();

        //2)根据zookeeper地址，获取具体的注册客户端的实例
        Registry registry = registryFactory.getRegistry(URL.valueOf("zookeeper://127.0.0.1:2181"));

        //3)将降级方案注册到zookeeper
        registry.register(URL.valueOf("override://0.0.0.0/com.lhl.apache.dubbo.sdk.GreetingService?category=configurators" +
                "&dynamic=false&application=first-dubbo-consumer&" + "mock=" + type + ":return+null&group=dubbo&version=1.0.0"));

        //4)取消配置
//        registry.unregister(URL.valueOf("override://0.0.0.0/com.lhl.apache.dubbo.sdk.GreetingService?category=configurators" +
//                "&dynamic=false&application=first-dubbo-consumer&" + "mock=" + type + ":return+null&group=dubbo&version=1.0.0"));
    }
}
