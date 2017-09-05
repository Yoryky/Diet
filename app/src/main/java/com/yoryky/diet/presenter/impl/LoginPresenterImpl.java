package com.yoryky.diet.presenter.impl;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.event.MeUpdateEvent;
import com.yoryky.diet.model.LoginModel;
import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.model.impl.LoginModelImpl;
import com.yoryky.diet.presenter.LoginPresenter;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.activity.MainActivity;
import com.yoryky.diet.ui.activity.RegisterActivity;
import com.yoryky.diet.ui.view.LoginView;
import com.yoryky.diet.util.HelpUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * Created by caicai on 2017/9/3.
 */

public class LoginPresenterImpl implements LoginPresenter,OnCustomListener{
    private LoginView loginView;
    private LoginModel loginModel;
    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void startRegisterActivity() {
        Intent intent = new Intent(loginView.getContext(), RegisterActivity.class);
        loginView.getContext().startActivity(intent);
    }

    @Override
    public void login() {
        loginModel.login(loginView.getUserName(),loginView.getPassword(),this);
    }

    @Override
    public void onSuccess(JSONObject json) {
        if(json.getIntValue("code") == 0){
            JSONObject data = json.getJSONObject("data");
            Intent intent = new Intent(loginView.getContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            loginView.getContext().startActivity(intent);
            JSONObject datajson = json.getJSONObject("data");
            User user = new User();
            user.setId(datajson.getString("id"));
            user.setPassword(datajson.getString("password"));
            user.setUser_name(datajson.getString("user_name"));
            MyApplication.getContext().setUser(user);
            SharedPreferences sharedPreferences = loginView.getContext().getSharedPreferences("user",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("id",user.getId());
            editor.putString("user_name",user.getUser_name());
            editor.putString("password",user.getPassword());
            editor.commit();
            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("user_name",data.getString("user_name"));
            EventBus.getDefault().post(new MeUpdateEvent(HelpUtil.getEventBusData("me",hashMap)));
        }else{
            HelpUtil.showToast(loginView.getContext(),json.getString("message"));
        }
    }


    @Override
    public void onError(JSONObject error) {

    }

    @Override
    public void back() {
        loginView.back();
    }
}
