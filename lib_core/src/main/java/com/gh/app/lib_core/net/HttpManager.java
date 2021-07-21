package com.gh.app.lib_core.net;


import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.openharmony.schedulers.OpenHarmonySchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: gh
 * @description:
 * @date: 2019-07-13.
 * @from:
 */
public class HttpManager {

    private volatile static HttpManager INSTANCE;
    private List<Interceptor> interceptorList;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpManager();
                }
            }
        }
        return INSTANCE;
    }

    public HttpManager setInterceptorList(List<Interceptor> interceptorList) {
        this.interceptorList = interceptorList;
        return this;
    }

    /**
     * 处理http请求
     *
     * @param baseApi
     */
    public void doHttpDeal(BaseApi baseApi) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(baseApi.getConnectionTime(), TimeUnit.SECONDS);

        if (interceptorList != null && interceptorList.size() > 0) {
            for (Interceptor in : interceptorList) {
                builder.addInterceptor(in);
            }
        }

        Retrofit retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseApi.getBaseUrl())
                .build();

        ProgressSubscriber subscriber = new ProgressSubscriber(baseApi);

        Flowable observable = baseApi.getObservable(retrofit)
                /*失败后的retry配置*/
//                .retryWhen(new RetryWhenNetworkException(baseApi.getRetryCount(),
//                        baseApi.getRetryDelay(), baseApi.getRetryIncreaseDelay()))
                /*生命周期管理*/
//                .compose(baseApi.getRxAppCompatActivity().bindUntilEvent(ActivityEvent.PAUSE))
                /*http请求线程*/
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                /*回调线程*/
                .observeOn(OpenHarmonySchedulers.mainThread())
                /*结果判断*/
                .map(baseApi);

        SoftReference<BaseHttpOnNextListener> httpOnNextListener = baseApi.getListener();
        if (httpOnNextListener != null && httpOnNextListener.get() != null) {
            httpOnNextListener.get().onNext(observable);
        }

        observable.subscribe(subscriber);

    }

}
