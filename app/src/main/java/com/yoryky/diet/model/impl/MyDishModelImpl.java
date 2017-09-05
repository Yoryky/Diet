package com.yoryky.diet.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.model.MyDishModel;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.util.volley.VolleyRequest;



import java.util.HashMap;

/**
 * Created by caicai on 2017/9/3.
 */

public class MyDishModelImpl implements MyDishModel {
    @Override
    public void getDishes(final OnCustomListener onCustomListener) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("command","getUserDish");
        hashMap.put("user_id", MyApplication.getContext().getUser().getId());
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getDishUrl(), hashMap, JSONObject.class,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null){
                            onCustomListener.onError(response);
                        }else{
                            onCustomListener.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
}
