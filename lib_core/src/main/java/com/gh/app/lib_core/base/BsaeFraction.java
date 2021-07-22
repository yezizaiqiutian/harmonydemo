package com.gh.app.lib_core.base;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.fraction.Fraction;
import ohos.aafwk.content.Intent;
import ohos.app.Context;

public class BsaeFraction extends Fraction {

    protected Ability ability;
    protected Context context;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        ability = getFractionAbility();
        context = ability.getContext();
    }

    /**
     * 登录状态变化
     * @param isLogin
     */
    public void onLoginStateChanged(boolean isLogin) {

    }
}
