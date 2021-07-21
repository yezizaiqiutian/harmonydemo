package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.harmonydemo.net.ApiService;
import com.gh.app.harmonydemo.net.HttpOnNextListener;
import com.gh.app.harmonydemo.net.NetUtils;
import io.reactivex.rxjava3.core.Flowable;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

public class NewsPageFraction extends Fraction {

    private String title;

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

        ListContainer listContainer = (ListContainer) getComponent().findComponentById(ResourceTable.Id_list_container);
        NewsPageItemProvider newsPageItemProvider = new NewsPageItemProvider(getFractionAbility(), new ArrayList<>());
        listContainer.setItemProvider(newsPageItemProvider);

        NetUtils.getNet(getContext(), new HttpOnNextListener<List<NewsBean>>() {
            @Override
            public Flowable onConnect(ApiService service) {
                return service.getNewsList();
            }

            @Override
            public void onNext(List<NewsBean> list) {
                newsPageItemProvider.updata(list);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

}
