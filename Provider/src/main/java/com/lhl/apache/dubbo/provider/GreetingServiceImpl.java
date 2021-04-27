package com.lhl.apache.dubbo.provider;

import com.alibaba.fastjson.JSON;
import com.lhl.apache.dubbo.sdk.GreetingService;
import com.lhl.apache.dubbo.sdk.PoJo;
import com.lhl.apache.dubbo.sdk.Result;
import org.apache.dubbo.rpc.RpcContext;

/**
 * 服务的具体实现
 * @author lvhonglei
 */
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello" + name +" " + RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(PoJo poJo) {
        Result<String> result = new Result<>();
        result.setSucces(Boolean.TRUE);

        result.setData(JSON.toJSONString(poJo));

        return result;
    }
}
