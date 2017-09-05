package com.yoryky.diet.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.event.MeUpdateEvent;
import com.yoryky.diet.model.LoginModel;
import com.yoryky.diet.model.MainModel;
import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.model.impl.LoginModelImpl;
import com.yoryky.diet.model.impl.MainModelImpl;
import com.yoryky.diet.presenter.MainPresenter;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.presenter.listener.OnPutUserListener;
import com.yoryky.diet.presenter.listener.OnUserListener;
import com.yoryky.diet.ui.activity.MainActivity;
import com.yoryky.diet.ui.view.MainView;
import com.yoryky.diet.util.HelpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.UUID;

import static android.content.ContentValues.TAG;

/**
 * Created by yoryky on 2017/8/29.
 */

public class MainPresenterImpl implements MainPresenter, OnUserListener,OnPutUserListener,OnCustomListener {
    private MainView mainView;
    private MainModel userModel;
    private LoginModel loginModel;
    public MainPresenterImpl(MainView mainView){
        this.mainView = mainView;
        this.userModel = new MainModelImpl();
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void getUser(String user_id) {
        userModel.getObjectUser(user_id,this);
    }

    @Override
    public void putUser() {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        userModel.putUser(user,this);
    }

    @Override
    public void autoLogin() {
        SharedPreferences sharedPreferences = mainView.getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id","");
        String user_name = sharedPreferences.getString("user_name","");
        String password = sharedPreferences.getString("password","");
        if(!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(password)){
            loginModel.login(user_name, password,this);
        }
    }

    @Override
    public void onError(JSONObject error) {

    }

    @Override
    public void onSuccess(JSONObject result) {
        JSONObject datajson = result.getJSONObject("data");
        User user = new User();
        user.setId(datajson.getString("id"));
        user.setPassword(datajson.getString("password"));
        user.setUser_name(datajson.getString("user_name"));
        MyApplication.getContext().setUser(user);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("user_name",datajson.getString("user_name"));
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
