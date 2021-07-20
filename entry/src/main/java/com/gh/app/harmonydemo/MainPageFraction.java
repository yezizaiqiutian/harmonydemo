package com.gh.app.harmonydemo;

import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;

public class MainPageFraction extends Fraction {

    private String title;

    public MainPageFraction(String  title) {
        this.title = title;
    }

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        Component component = scatter.parse(ResourceTable.Layout_fraction_main, container, false);

        Text Id_tv_text = (Text) component.findComponentById(ResourceTable.Id_tv_text);
        Id_tv_text.setText(title);

        return component;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);

    }

}
