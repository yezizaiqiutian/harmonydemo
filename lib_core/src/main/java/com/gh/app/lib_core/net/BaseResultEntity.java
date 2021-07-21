package com.gh.app.lib_core.net;

public interface BaseResultEntity<T> {

    public  boolean isSuccess();

    public String getCode();

    public String getMsg();

    public T getData();

}
