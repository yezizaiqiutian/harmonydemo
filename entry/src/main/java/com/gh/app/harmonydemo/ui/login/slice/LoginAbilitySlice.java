package com.gh.app.harmonydemo.ui.login.slice;

import com.gh.app.harmonydemo.ResourceTable;
import com.gh.app.harmonydemo.bean.NewsBean;
import com.gh.app.harmonydemo.bean.UserBean;
import com.gh.app.harmonydemo.constant.Constant;
import com.gh.app.harmonydemo.net.ApiService;
import com.gh.app.harmonydemo.net.HttpOnNextListener;
import com.gh.app.harmonydemo.net.NetUtils;
import com.gh.app.lib_core.sp.SpUtils;
import io.reactivex.rxjava3.core.Flowable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.RadioContainer;
import ohos.agp.components.TextField;
import ohos.agp.window.dialog.ToastDialog;

import java.util.List;

public class LoginAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_login);
        initView();
    }

    private void initView() {
        Button Id_btn_login = (Button) findComponentById(ResourceTable.Id_btn_login);
        TextField Id_tv_username = (TextField) findComponentById(ResourceTable.Id_tv_username);
        TextField Id_tv_password = (TextField) findComponentById(ResourceTable.Id_tv_password);
        Id_btn_login.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                NetUtils.getNet(getContext(), new HttpOnNextListener<UserBean>() {
                    @Override
                    public Flowable onConnect(ApiService service) {
                        return service.login(Id_tv_username.getText(), Id_tv_password.getText());
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        new ToastDialog(getContext()).setText("登录成功").show();
                        SpUtils.setObject(getContext(), Constant.USER_INFO, userBean);

                        Intent intent = new Intent();
                        intent.setParam(Constant.RST_LOGIN, true);
                        setResult(intent);
                        onBackPressed();
                    }

                    @Override
                    public void onError(Throwable e) {
                        new ToastDialog(getContext()).setText(e.getMessage()).show();
                    }
                });
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
