package com.gh.app.feature_card.widget.news_widget;

import com.gh.app.feature_card.ResourceTable;
import com.gh.app.feature_card.widget.controller.FormController;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.ProviderFormInfo;
import ohos.aafwk.content.Intent;
import ohos.agp.components.ComponentProvider;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.HashMap;
import java.util.Map;

public class NewswidgetImpl extends FormController {
    private static final HiLogLabel TAG = new HiLogLabel(HiLog.DEBUG, 0x0, NewswidgetImpl.class.getName());
    private static final int DEFAULT_DIMENSION_2X2 = 2;
    private static final Map<Integer, Integer> RESOURCE_ID_MAP = new HashMap<>();

    static {
        RESOURCE_ID_MAP.put(DEFAULT_DIMENSION_2X2, ResourceTable.Layout_form_grid_pattern_widget_2_2);
    }

    public NewswidgetImpl(Context context, String formName, Integer dimension) {
        super(context, formName, dimension);
    }

    @Override
    public ProviderFormInfo bindFormData() {
        HiLog.info(TAG, "bind form data when create form");
        ProviderFormInfo providerFormInfo = new ProviderFormInfo(RESOURCE_ID_MAP.get(dimension), context);

        //修改卡片文本内容
        ComponentProvider componentProvider = new ComponentProvider(RESOURCE_ID_MAP.get(dimension), context);
        componentProvider.setImageContent(ResourceTable.Id_iv_img, ResourceTable.Media_icon_header_bai);
        componentProvider.setText(ResourceTable.Id_tv_text1, "皮哥666");
        componentProvider.setText(ResourceTable.Id_tv_text2, "我要向皮哥学习");
        providerFormInfo.mergeActions(componentProvider);

        return providerFormInfo;
    }

    @Override
    public void updateFormData(long formId, Object... vars) {
        HiLog.info(TAG, "update form data timing, default 30 minutes");
    }

    @Override
    public void onTriggerFormEvent(long formId, String message) {
        HiLog.info(TAG, "handle card click event.");
    }

    @Override
    public Class<? extends AbilitySlice> getRoutePageSlice(Intent intent) {
        HiLog.info(TAG, "get the default page to route when you click card.");
        return NewsAbilitySlice.class;
    }
}