package com.gh.app.lib_core.base;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.FractionAbility;
import ohos.aafwk.content.Intent;
import ohos.app.Context;

public class BsaeAbility extends FractionAbility {

    protected Ability ability;
    protected Context context;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        ability = this;
        context = this;
    }
}
