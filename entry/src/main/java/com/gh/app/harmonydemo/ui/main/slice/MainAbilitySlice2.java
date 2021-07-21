package com.gh.app.harmonydemo.ui.main.slice;

import com.gh.app.harmonydemo.ui.main.fraction.MainPageFraction;
import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.ui.main.fraction.MinePageFraction;
import com.gh.app.harmonydemo.ui.main.fraction.MorePageFraction;
import com.gh.app.harmonydemo.ui.main.fraction.NewsPageFraction;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.ability.fraction.FractionScheduler;
import ohos.aafwk.content.Intent;
import ohos.agp.components.RadioContainer;
import ohos.agp.window.dialog.ToastDialog;

import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice2 extends AbilitySlice {

    private List<Fraction> fractionList = new ArrayList<>();
    private int currentSaveIndex;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main2);
        initView();
    }

    private void initView() {
        new ToastDialog(getContext()).setText("欢迎回来").show();

        fractionList.clear();
        fractionList.add(new MainPageFraction("首页"));
        fractionList.add(new NewsPageFraction("新闻"));
        fractionList.add(new MorePageFraction("更多"));
        fractionList.add(new MinePageFraction("我的"));

        FractionScheduler fractionScheduler = ((FractionAbility) getAbility()).getFractionManager().startFractionScheduler();
        for (Fraction fraction : fractionList) {
            fractionScheduler.add(ResourceTable.Id_main_content, fraction);
        }
        fractionScheduler.show(fractionList.get(0));
        fractionScheduler.submit();

        RadioContainer container = (RadioContainer) findComponentById(ResourceTable.Id_radio_container);

        container.setMarkChangedListener(new RadioContainer.CheckedStateChangedListener() {
            @Override
            public void onCheckedChanged(RadioContainer radioContainer, int index) {
                ((FractionAbility) getAbility()).getFractionManager().startFractionScheduler()
                        .hide(fractionList.get(currentSaveIndex))
                        .show(fractionList.get(index))
                        .submit();
                currentSaveIndex = index;
            }
        });

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
