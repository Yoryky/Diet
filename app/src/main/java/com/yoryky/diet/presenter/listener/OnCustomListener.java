package com.yoryky.diet.presenter.listener;


import com.alibaba.fastjson.JSONObject;

/**
 * Created by yoryky on 2017/8/30.
 */

public interface OnCustomListener {
    public void onSuccess(JSONObject result);
    public void onError(JSONObject error);
}
