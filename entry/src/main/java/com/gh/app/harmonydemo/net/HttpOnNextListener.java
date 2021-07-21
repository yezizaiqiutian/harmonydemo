package com.gh.app.harmonydemo.net;

import com.gh.app.lib_core.net.BaseHttpOnNextListener;
import io.reactivex.rxjava3.core.Flowable;

public abstract class HttpOnNextListener<T>  extends BaseHttpOnNextListener<T> {

    public abstract Flowable onConnect(ApiService service);

}
