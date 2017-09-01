package com.yoryky.diet.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.yoryky.diet.R;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.SettingView;

/**
 * Created by yoryky on 2017/9/1.
 */

public class SettingActivity extends BaseActivity implements SettingView, View.OnClickListener{
    private LinearLayout llLogin;
    private LinearLayout llExit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        this.initViews();
    }

    private void initViews() {
        llLogin = (LinearLayout)findViewById(R.id.ll_login);
        llExit = (LinearLayout)findViewById(R.id.ll_exit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_login:

                break;
            case R.id.ll_exit:

                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void exit() {

    }
}
