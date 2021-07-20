package com.gh.app.harmonydemo.ui.main;

import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;

import java.util.List;

public class MainPageProvider extends PageSliderProvider {

    // 数据源，每个页面对应list中的一项
    private List<String> list;
    public MainPageProvider(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object createPageInContainer(ComponentContainer componentContainer, int i) {
        final String data = list.get(i);
        Text label = new Text(null);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setLayoutConfig(
                new StackLayout.LayoutConfig(
                        ComponentContainer.LayoutConfig.MATCH_PARENT,
                        ComponentContainer.LayoutConfig.MATCH_PARENT
                ));
        label.setText(data);
        label.setTextColor(Color.BLACK);
        label.setTextSize(50);
        ShapeElement element = new ShapeElement();
        element.setRgbColor(RgbColor.fromArgbInt(Color.getIntColor("#AFEEEE")));
        label.setBackground(element);
        componentContainer.addComponent(label);
        return label;
    }

    @Override
    public void destroyPageFromContainer(ComponentContainer componentContainer, int i, Object o) {
        componentContainer.removeComponent((Component) o);
    }

    @Override
    public boolean isPageMatchToObject(Component component, Object o) {
        return true;
    }
}
