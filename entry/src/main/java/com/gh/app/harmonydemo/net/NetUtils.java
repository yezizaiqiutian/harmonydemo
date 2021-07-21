package com.gh.app.harmonydemo.net;

import com.gh.app.lib_core.net.HttpManager;
import io.reactivex.rxjava3.core.Flowable;
import ohos.app.Context;
import retrofit2.Retrofit;

public class NetUtils {

    /**
     * 请求网咯,无加载框
     *
     * @param context
     * @param simpleOnNextListener
     */
    public static void getNet(Context context, HttpOnNextListener simpleOnNextListener) {
        HttpManager manager = HttpManager.getInstance();
        manager
//                .setInterceptorList(interceptorList)
                .doHttpDeal(new NetApi(simpleOnNextListener, context) {
                    @Override
                    public Flowable getObservable(Retrofit retrofit) {
                        ApiService service = retrofit.create(ApiService.class);
                        return simpleOnNextListener.onConnect(service);
                    }
                });
    }

}
