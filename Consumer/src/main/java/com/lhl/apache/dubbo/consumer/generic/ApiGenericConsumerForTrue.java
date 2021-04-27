package com.lhl.apache.dubbo.consumer.generic;

import com.alibaba.fastjson.JSON;
import com.lhl.apache.dubbo.sdk.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

import java.util.HashMap;
import java.util.Map;

/**
 * 泛化调用
 * generic=true
 * @author lvhonglei
 */
public class ApiGenericConsumerForTrue {

    public static void main(String[] args){
        //1)创建引用实例，并设置
        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        referenceConfig.setRegistry(new RegistryConfig("zookeeper://127.0.0.1:2181"));
        referenceConfig.setVersion("1.0.0");
        referenceConfig.setGroup("dubbo");

        //2)设置为泛化引用
        referenceConfig.setInterface(GreetingService.class);
        referenceConfig.setGeneric(Boolean.TRUE);

        //3)用GendricService替代所有引用
        GenericService genericService = referenceConfig.get();

        //4)泛型调用
        Object result = genericService.$invoke("sayHello",new String[]{"java.lang.String"},new Object[]{"world"});
        System.out.println(JSON.toJSONString(result));

        //5)PoJo参数转Map
        Map<String,Object> map = new HashMap<>();
        map.put("class","com.lhl.apache.dubbo.sdk.PoJo");
        map.put("id","8105");
        map.put("name","lhl");

        //6)发起泛化调用
        result = genericService.$invoke("testGeneric",new String[]{"com.lhl.apache.dubbo.sdk.PoJo"},new Object[]{map});
        System.out.println(result);
    }

}
