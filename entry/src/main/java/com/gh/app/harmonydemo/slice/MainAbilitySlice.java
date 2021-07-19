package com.gh.app.harmonydemo.slice;

import com.gh.app.harmonydemo.MainPageProvider;
import com.gh.app.harmonydemo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.PageSlider;
import ohos.agp.components.RadioButton;
import ohos.agp.components.RadioContainer;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    private void initView() {
        PageSlider pageSlider = (PageSlider) findComponentById(ResourceTable.Id_page_slider);
        RadioContainer container = (RadioContainer) findComponentById(ResourceTable.Id_radio_container);
        RadioButton Id_radio_button_1 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_1);
        RadioButton Id_radio_button_2 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_2);
        RadioButton Id_radio_button_3 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_3);
        RadioButton Id_radio_button_4 = (RadioButton) findComponentById(ResourceTable.Id_radio_button_4);

        pageSlider.setProvider(new MainPageProvider(getData()));

        container.setMarkChangedListener(new RadioContainer.CheckedStateChangedListener() {
            @Override
            public void onCheckedChanged(RadioContainer radioContainer, int index) {
                pageSlider.setCurrentPage(index);
            }
        });

        pageSlider.addPageChangedListener(new PageSlider.PageChangedListener() {
            @Override
            public void onPageSliding(int i, float v, int i1) {

            }

            @Override
            public void onPageSlideStateChanged(int i) {

            }

            @Override
            public void onPageChosen(int i) {
                switch (i) {
                    case 0:
                        Id_radio_button_1.setChecked(true);
                        break;
                    case 1:
                        Id_radio_button_2.setChecked(true);
                        break;
                    case 2:
                        Id_radio_button_3.setChecked(true);
                        break;
                    case 3:
                        Id_radio_button_4.setChecked(true);
                        break;
                }
            }
        });

    }

    private List<String> getData() {
        ArrayList<String> dataItems = new ArrayList<>();
        dataItems.add("Page A");
        dataItems.add("Page B");
        dataItems.add("Page C");
        dataItems.add("Page D");
        return dataItems;
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
