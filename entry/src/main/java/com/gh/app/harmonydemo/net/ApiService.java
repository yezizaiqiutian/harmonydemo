package com.gh.app.harmonydemo.net;

import com.gh.app.harmonydemo.bean.NewsBean;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;

import java.util.List;


public interface ApiService {
    @GET("news/list")
    Flowable<BaseEntity<List<NewsBean>>> getNewsList();
}