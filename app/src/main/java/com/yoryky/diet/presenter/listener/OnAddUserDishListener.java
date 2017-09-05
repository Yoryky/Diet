package com.yoryky.diet.presenter.listener;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by caicai on 2017/9/4.
 */

public interface OnAddUserDishListener {
    void onAddSuccess(JSONObject result);
    void onAddError(JSONObject error);
}
