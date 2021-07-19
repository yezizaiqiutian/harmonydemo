package com.gh.app.feature_card.widget.news_widget;

import com.gh.app.feature_card.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;

public class NewsAbilitySlice extends AbilitySlice {

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_slice_news);
//        initView();
//        configWebView();
//        webView.load("https://h.5i5j.com/calculator/#/");
    }


}
