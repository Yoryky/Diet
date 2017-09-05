package com.yoryky.diet.model;

import com.yoryky.diet.presenter.listener.OnCustomListener;

/**
 * Created by yoryky on 2017/9/1.
 */

public interface RegisterModel {
    void register(String user_name, String password, OnCustomListener listener);
}
