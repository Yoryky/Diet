package com.yoryky.diet.presenter.impl;

import android.content.Intent;
import android.util.Log;

import com.yoryky.diet.model.MainModel;
import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.model.impl.MainModelImpl;
import com.yoryky.diet.presenter.MainPresenter;
import com.yoryky.diet.presenter.listener.OnPutUserListener;
import com.yoryky.diet.presenter.listener.OnUserListener;
import com.yoryky.diet.ui.activity.MainActivity;
import com.yoryky.diet.ui.view.MainView;

import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by yoryky on 2017/8/29.
 */

public class MainPresenterImpl implements MainPresenter, OnUserListener,OnPutUserListener {
    private MainView mainView;
    private MainModel userModel;

    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        this.userModel = new MainModelImpl();
    }

    @Override
    public void getUser(String user_id) {
        //userModel.getAllUser(user_id,this);
        userModel.getObjectUser(user_id,this);
    }

    @Override
    public void putUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        userModel.putUser(user,this);
    }

    @Override
    public void onSuccess(User user) {
        String name = user.getName();
        Log.d(TAG, "onSuccess: " + name);
    }

    @Override
    public void onError() {
        Log.d(TAG, "onError: error");
    }

    @Override
    public void onError(String result) {
        Log.d(TAG, "onError: " + result);
    }


    @Override
    public void onSuccess(String result) {
        Log.d(TAG, "onSuccess: " + result);
    }
}
