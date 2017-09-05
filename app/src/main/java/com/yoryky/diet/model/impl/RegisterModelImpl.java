package com.yoryky.diet.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.model.RegisterModel;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.util.volley.VolleyRequest;

import java.util.HashMap;

/**
 * Created by yoryky on 2017/9/1.
 */

public class RegisterModelImpl implements RegisterModel {

    @Override
    public void register(String user_name, String password, final OnCustomListener listener) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("command", "register");
        hashMap.put("user_name", user_name);
        hashMap.put("password", password);
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getUserUrl(), hashMap, JSONObject.class,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response != null) {
                            listener.onSuccess(response);
                        } else {
                            listener.onError(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

}
