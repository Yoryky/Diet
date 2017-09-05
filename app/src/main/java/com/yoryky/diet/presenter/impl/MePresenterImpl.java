package com.yoryky.diet.presenter.impl;

import android.content.Intent;
import android.text.TextUtils;

import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.presenter.MePresenter;
import com.yoryky.diet.ui.activity.MyDishActivity;
import com.yoryky.diet.ui.activity.SettingActivity;
import com.yoryky.diet.ui.view.MeView;

/**
 * Created by caicai on 2017/9/3.
 */

public class MePresenterImpl implements MePresenter {
    private MeView meView;
    public MePresenterImpl(MeView meView){
        this.meView = meView;
    }
    @Override
    public void startDishListActivity() {
        Intent intent = new Intent(meView.getContext(), MyDishActivity.class);
        meView.getContext().startActivity(intent);
    }

    @Override
    public void startEditInfoActivity() {

    }

    @Override
    public void startSettingActivity() {
        Intent intent = new Intent(meView.getContext(), SettingActivity.class);
        meView.getContext().startActivity(intent);
    }

    @Override
    public void setUserName() {
        User user = MyApplication.getContext().getUser();
        if(user != null && !TextUtils.isEmpty(user.getUser_name())){
            meView.setUserName(user.getUser_name());
        }
    }
}
