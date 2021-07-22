package com.gh.app.harmonydemo.ui.main.fraction;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.UserBean;
import com.gh.app.harmonydemo.constant.Constant;
import com.gh.app.harmonydemo.ui.login.LoginAbility;
import com.gh.app.lib_core.base.BsaeFraction;
import com.gh.app.lib_core.sp.SpUtils;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

public class MinePageFraction extends BsaeFraction {

    private AbilitySlice abilitySlice;
    private UserBean userBean;
    private Text id_tv_text;

    public MinePageFraction(AbilitySlice abilitySlice) {
        this.abilitySlice = abilitySlice;
    }

    @Override
    protected Component onComponentAttached(LayoutScatter scatter, ComponentContainer container, Intent intent) {
        Component component = scatter.parse(ResourceTable.Layout_fraction_mine, container, false);
        return component;
    }

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        id_tv_text = (Text) getComponent().findComponentById(ResourceTable.Id_tv_text);
        initView();
    }

    @Override
    public void onLoginStateChanged(boolean isLogin) {
        initView();
    }

    private void initView() {
        userBean = SpUtils.getObject(getFractionAbility().getContext(), Constant.USER_INFO, UserBean.class);

        if (userBean == null) {
            id_tv_text.setText("请登录");
        } else {
            id_tv_text.setText("您已登录,点击可推出");
        }

        id_tv_text.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                if (userBean == null) {
                    new ToastDialog(getFractionAbility().getContext()).setText("去登录").show();
                    LoginAbility.access(abilitySlice, Constant.CODE_LOGIN_REQ);
                } else {
                    SpUtils.setObject(getFractionAbility().getContext(), Constant.USER_INFO, null);
                    new ToastDialog(getFractionAbility().getContext()).setText("退出登录").show();
                    initView();
                }
            }
        });
    }
}
