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
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainPageFraction extends BsaeFraction {

    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, MainPageFraction.class.getName());
    private String title;

    private ListContainer listContainer;
    private Text tv_empty;
    private MainPageItemProvider mainPageItemProvider;

    public MainPageFraction(String title) {
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

        mainPageItemProvider = new MainPageItemProvider(getFractionAbility(), new ArrayList<>());
        listContainer.setItemProvider(mainPageItemProvider);

        getNet();

        tv_empty.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                getNet();
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
                mainPageItemProvider.updata(list);
            }

            @Override
            public void onError(Throwable e) {
                tv_empty.setVisibility(Component.VISIBLE);
                listContainer.setVisibility(Component.HIDE);
            }
        });
    }

}
