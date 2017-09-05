package com.yoryky.diet.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yoryky.diet.R;
import com.yoryky.diet.presenter.RegisterPresenter;
import com.yoryky.diet.presenter.impl.RegisterPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.RegisterView;

/**
 * Created by yoryky on 2017/9/1.
 */

public class RegisterActivity extends BaseActivity implements RegisterView,View.OnClickListener{
    private RelativeLayout rlBack;
    private EditText etUserName;
    private EditText etPwd;
    private EditText etPwdConfirm;
    private Button btnRegister;
    private RegisterPresenter registerPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.initViews();
    }

    private void initViews(){
        registerPresenter = new RegisterPresenterImpl(this);
        rlBack = (RelativeLayout)findViewById(R.id.rl_back);
        etUserName = (EditText)findViewById(R.id.et_username);
        etPwd = (EditText)findViewById(R.id.et_password);
        etPwdConfirm = (EditText)findViewById(R.id.et_password_confirm);
        btnRegister = (Button)findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                registerPresenter.register();
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPwd.getText().toString();
    }

    @Override
    public String getPwdConfirm() {
        return etPwdConfirm.getText().toString();
    }
}
