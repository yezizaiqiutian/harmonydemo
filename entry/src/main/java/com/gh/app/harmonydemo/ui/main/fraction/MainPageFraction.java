package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.List;

public class MainPageFraction extends Fraction {

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
        MainPageItemProvider mainPageItemProvider = new MainPageItemProvider(getFractionAbility(), getData());
        listContainer.setItemProvider(mainPageItemProvider);

    }

    private List<NewsBean> getData() {
        List<NewsBean> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new NewsBean("高祥","祥哥很牛逼"));
            list.add(new NewsBean("蔡奇峰","峰哥很牛逼"));
            list.add(new NewsBean("李伟","伟哥很牛逼,嗯,这个确实很牛逼"));
            list.add(new NewsBean("新闻列表1","吴亦凡..................."));
        }
        return list;
    }

}
