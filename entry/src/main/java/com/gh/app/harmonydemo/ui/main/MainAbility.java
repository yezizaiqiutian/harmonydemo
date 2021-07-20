package com.gh.app.harmonydemo.ui.main;

import com.gh.app.harmonydemo.ui.main.slice.MainAbilitySlice2;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;

public class MainAbility extends FractionAbility {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice2.class.getName());
    }
}
