package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.harmonydemo.net.ApiService;
import com.gh.app.lib_core.http.ApiStore;
import com.gh.app.lib_core.http.BaseResultEntity;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.openharmony.schedulers.OpenHarmonySchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.ArrayList;
import java.util.List;

public class MainPageFraction extends Fraction {
    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, MainPageFraction.class.getName());


    private String title;

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

        ListContainer listContainer = (ListContainer) getComponent().findComponentById(ResourceTable.Id_list_container);
        MainPageItemProvider mainPageItemProvider = new MainPageItemProvider(getFractionAbility(), new ArrayList<>());
        listContainer.setItemProvider(mainPageItemProvider);

        ApiStore.getBaseService(ApiService.class).getNewsList()
                .subscribeOn(Schedulers.io())
                .observeOn(OpenHarmonySchedulers.mainThread())
                .subscribe(new Consumer<BaseResultEntity<List<NewsBean>>>() {
                    @Override
                    public void accept(BaseResultEntity<List<NewsBean>> list) throws Throwable {
                        mainPageItemProvider.updata(list.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        new ToastDialog(getFractionAbility().getContext()).setText("接口获取失败:" + throwable.getMessage()).show();
                    }
                });
    }

}
