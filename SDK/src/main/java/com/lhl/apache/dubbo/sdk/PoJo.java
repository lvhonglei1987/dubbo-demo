package com.lhl.apache.dubbo.sdk;

/**
 * 简单Java对象
 * @author lvhonglei
 */
public class PoJo {
    private String id;
    private String name;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
