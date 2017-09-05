package com.yoryky.diet.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.presenter.SettingPresenter;
import com.yoryky.diet.ui.activity.LoginActivity;
import com.yoryky.diet.ui.view.SettingView;

/**
 * Created by caicai on 2017/9/1.
 */

public class SettingPresenterImpl implements SettingPresenter {
    private SettingView settingView;
    public SettingPresenterImpl(SettingView settingView){
        this.settingView = settingView;
    }
    @Override
    public void startLoginActivity() {
        Intent intent = new Intent(settingView.getContext(), LoginActivity.class);
        settingView.getContext().startActivity(intent);
    }

    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(settingView.getContext(),LoginActivity.class);
        settingView.getContext().startActivity(intent);
    }

    @Override
    public void back() {
        settingView.back();
    }

    @Override
    public void exit() {
        SharedPreferences sharedPreferences = settingView.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id","");
        editor.putString("user_name","");
        editor.putString("password","");
        editor.commit();
        MyApplication.getContext().setUser(null);
        Intent intent = new Intent(settingView.getContext(),LoginActivity.class);
        settingView.getContext().startActivity(intent);
    }
}
