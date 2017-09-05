package com.yoryky.diet.model;

import com.yoryky.diet.presenter.listener.OnCustomListener;

/**
 * Created by caicai on 2017/9/3.
 */

public interface LoginModel {
    void login(String user_name, String password, OnCustomListener listener);
}
