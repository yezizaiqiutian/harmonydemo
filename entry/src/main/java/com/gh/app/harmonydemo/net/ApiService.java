package com.gh.app.harmonydemo.net;

import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.lib_core.http.BaseResultEntity;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

import java.util.List;


public interface ApiService {
    @GET("news/list")
    Observable<BaseResultEntity<List<NewsBean>>> getNewsList();
}