package com.yoryky.diet.presenter.impl;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.model.RegisterModel;
import com.yoryky.diet.model.impl.RegisterModelImpl;
import com.yoryky.diet.presenter.RegisterPresenter;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.activity.LoginActivity;
import com.yoryky.diet.ui.view.RegisterView;
import com.yoryky.diet.util.HelpUtil;

/**
 * Created by caicai on 2017/9/1.
 */

public class RegisterPresenterImpl implements RegisterPresenter, OnCustomListener {
    private RegisterView registerView;
    private RegisterModel registerModel;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        this.registerModel = new RegisterModelImpl();
    }

    @Override
    public void register() {
        String user_name = registerView.getUserName();
        String password = registerView.getPassword();
        String pwdConfirm = registerView.getPwdConfirm();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(pwdConfirm)) {
            HelpUtil.showToast(registerView.getContext(), "请输入密码！");
            return;
        }
        if (!password.equals(pwdConfirm)) {
            HelpUtil.showToast(registerView.getContext(),"两次密码不一致！");
            return;
        }
        registerModel.register(user_name, password, this);
    }

    @Override
    public void onSuccess(JSONObject json) {
        if (json.getIntValue("code") == 0) {
            HelpUtil.showToast(registerView.getContext(), json.getString("message"));
            Intent intent = new Intent(registerView.getContext(), LoginActivity.class);
            registerView.getContext().startActivity(intent);
        } else {
            HelpUtil.showToast(registerView.getContext(), json.getString("message"));
        }
    }

    @Override
    public void onError(JSONObject error) {
        HelpUtil.showToast(registerView.getContext(), error.getString("message"));
    }
}
