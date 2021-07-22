package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.lib_core.base.BsaeFraction;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;

public class MorePageFraction extends BsaeFraction {

    private String title;

    public MorePageFraction(String  title) {
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
