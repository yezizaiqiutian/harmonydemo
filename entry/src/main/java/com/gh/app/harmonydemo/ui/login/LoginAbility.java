package com.gh.app.harmonydemo.ui.login;

import com.gh.app.harmonydemo.constant.Constant;
import com.gh.app.harmonydemo.ui.login.slice.LoginAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.app.Context;

public class LoginAbility extends Ability {

    public static void access(AbilitySlice context, int requestCode) {
        Intent intent = new Intent();

        // 通过Intent中的OperationBuilder类构造operation对象，指定设备标识（空串表示当前设备）、应用包名、Ability名称
        Operation operation = new Intent.OperationBuilder()
                .withDeviceId("")
                .withBundleName("com.gh.app.harmonydemo")
                .withAbilityName("com.gh.app.harmonydemo.ui.login.LoginAbility")
                .build();

        // 把operation设置到intent中
        intent.setOperation(operation);
        context.startAbilityForResult(intent, requestCode);
    }

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(LoginAbilitySlice.class.getName());

//        Intent intent2 = new Intent();
//        intent2.setParam(Constant.RST_LOGIN, true);
//        setResult(Constant.CODE_LOGIN_RSU,intent2);
    }
}
