package com.lhl.apache.dubbo.sdk;

import java.io.Serializable;

/**
 * 返回结果，范型
 * @author lvhonglei
 * @param <T>
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 5417111675046995511L;
    /**
     * 结果数据
     */
    private T data;

    private Boolean succes;
    private String msg;


    public void setData(T data){
        this.data = data;
    }

    public T getData(){
        return data;
    }

    public void setSucces(Boolean succes){
        this.succes = succes;
    }

    public Boolean getSucces(){
        return succes;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return msg;
    }
}
