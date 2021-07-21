package com.gh.app.harmonydemo.net;

import com.gh.app.lib_core.net.BaseApi;
import io.reactivex.rxjava3.core.Flowable;
import ohos.app.Context;
import retrofit2.Retrofit;

public class NetApi<T> extends BaseApi<T> {

    public NetApi(HttpOnNextListener listener, Context context) {
        super(context, listener);
        setBaseUrl(getUrl());
        setCancel(false);
        setShowProgress(false);
    }

    @Override
    public Flowable getObservable(Retrofit retrofit) {
        return null;
    }

    public String getUrl() {
        return "http://10.1.10.85:8888/";
    }

}
