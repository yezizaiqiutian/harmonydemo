package com.gh.app.harmonydemo.net;

import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.harmonydemo.bean.UserBean;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;


public interface ApiService {
    @GET("news/list")
    Flowable<BaseEntity<List<NewsBean>>> getNewsList();

    @FormUrlEncoded()
    @POST("user/login")
    Flowable<BaseEntity<UserBean>> login(@Field("user_name") String user_name, @Field("pass_word") String pass_word);
}