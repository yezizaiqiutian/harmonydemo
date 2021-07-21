package com.gh.app.lib_core.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ApiStore {
    static Map<String, Object> map;

    public static <T> T getBaseService(@NotNull Class<T> apiService) {
        return getApiService("http://10.1.10.85:8888/", apiService);
    }

    private static Retrofit.Builder getBuilder(String apiUrl) {
        //配置okHttp
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
                .readTimeout(30, TimeUnit.SECONDS);

        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        return new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build());
    }

    //ApiService工厂
    public static <T> T getApiService(@NotNull String url, @NotNull Class<T> apiService) {
        if (map == null) {
            map = new HashMap<>();
        }
        String key = url + "_" + apiService.getSimpleName();
        T service = map.containsKey(key) ? (T) map.get(key) : null;
        if (service == null) {
            service = getBuilder(url).build().create(apiService);
            map.put(key, service);
        }

        return service;
    }
}
