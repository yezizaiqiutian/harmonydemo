package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.harmonydemo.net.ApiService;
import com.gh.app.harmonydemo.net.HttpOnNextListener;
import com.gh.app.harmonydemo.net.NetUtils;
import com.gh.app.lib_core.base.BsaeFraction;
import io.reactivex.rxjava3.core.Flowable;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

public class NewsPageFraction extends BsaeFraction {

    private String title;

    private ListContainer listContainer;
    private Text tv_empty;
    private NewsPageItemProvider newsPageItemProvider;

    public NewsPageFraction(String  title) {
        this.title = title;
    }

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        Component component = scatter.parse(ResourceTable.Layout_fraction_mainpage, container, false);
        return component;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);

        listContainer = (ListContainer) getComponent().findComponentById(ResourceTable.Id_list_container);
        tv_empty = (Text) getComponent().findComponentById(ResourceTable.Id_tv_empty);

        newsPageItemProvider = new NewsPageItemProvider(getFractionAbility(), new ArrayList<>());
        listContainer.setItemProvider(newsPageItemProvider);

        tv_empty.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                tv_empty.setText("加载中...");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getNet();
                    }
                }).start();
            }
        });

    }

    private void getNet() {
        NetUtils.getNet(getFractionAbility().getContext(), new HttpOnNextListener<List<NewsBean>>() {
            @Override
            public Flowable onConnect(ApiService service) {
                return service.getNewsList();
            }

            @Override
            public void onNext(List<NewsBean> list) {
                listContainer.setVisibility(Component.VISIBLE);
                tv_empty.setVisibility(Component.HIDE);
                newsPageItemProvider.updata(list);
            }

            @Override
            public void onError(Throwable e) {
                tv_empty.setText("网络连接失败,点击重试");
                tv_empty.setVisibility(Component.VISIBLE);
                listContainer.setVisibility(Component.HIDE);
            }
        });
    }

}
