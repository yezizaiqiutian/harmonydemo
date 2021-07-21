package com.gh.app.harmonydemo.net;

import com.gh.app.lib_core.net.BaseResultEntity;

import java.io.Serializable;

public class BaseEntity<T> implements BaseResultEntity<T>, Serializable {

    private String code;
    private String msg;
    private T data;

    public String getStatus() {
        return code == null ? "" : code;
    }

    public void setStatus(String status) {
        this.code = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean isSuccess() {
        return "200".equals(code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public T getData() {
        return data;
    }

}
