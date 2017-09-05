package com.yoryky.diet.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yoryky.diet.R;
import com.yoryky.diet.presenter.impl.LoginPresenterImpl;
import com.yoryky.diet.presenter.LoginPresenter;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.LoginView;

/**
 * Created by yoryky on 2017/9/1.
 */

public class LoginActivity extends BaseActivity implements LoginView, View.OnClickListener {
    private RelativeLayout rlBack;
    private EditText etUserName;
    private EditText etPwd;
    private Button btnLogin;
    private Button btnRegister;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.initViews();
    }

    private void initViews() {
        loginPresenter = new LoginPresenterImpl(this);
        rlBack = (RelativeLayout)findViewById(R.id.rl_back);
        etUserName = (EditText)findViewById(R.id.et_username);
        etPwd = (EditText)findViewById(R.id.et_password);
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnRegister = (Button)findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        rlBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                loginPresenter.login();
                break;
            case R.id.btn_register:
                loginPresenter.startRegisterActivity();
                break;
            case R.id.rl_back:
                loginPresenter.back();
                break;
        }
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPwd.getText().toString();
    }
}
