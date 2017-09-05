package com.yoryky.diet.app;

import android.app.Application;

import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.util.volley.VolleyRequest;

/**
 * Created by yoryky on 2017/8/28.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    private String rootUrl;
    private User user;

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyRequest.buildRequestQueue(this);
        initRootUrl(false);
    }

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getContext() {
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private void initRootUrl(boolean isHome) {
        if (isHome) {
            rootUrl = "http://192.168.0.105:8081";
        } else {
            rootUrl = "http://192.168.3.102:8081";
        }
    }

    public String getDishUrl() {
        return rootUrl + "/dish/";
    }

    public String getUserUrl() {
        return rootUrl + "/user/";
    }
}
