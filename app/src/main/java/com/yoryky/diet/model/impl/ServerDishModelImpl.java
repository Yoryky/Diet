package com.yoryky.diet.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.model.ServerDishModel;
import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.presenter.listener.OnAddUserDishListener;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.util.volley.VolleyRequest;

import java.util.HashMap;

/**
 * Created by yoryky on 2017/9/4.
 */

public class ServerDishModelImpl implements ServerDishModel {
    @Override
    public void getSearchData(String searchName,int pageIndex,int pageSize, final OnCustomListener listener) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("command","all");
        hashMap.put("search_name",searchName);
        hashMap.put("page_index",String.valueOf(pageIndex));
        hashMap.put("page_size",String.valueOf(pageSize));
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getDishUrl(), hashMap, JSONObject.class,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            listener.onSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }

    @Override
    public void addUserDishData(Dish dish,final OnAddUserDishListener listener) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("command","addUserDish");
        hashMap.put("user_id",MyApplication.getContext().getUser().getId());
        hashMap.put("dish_id",dish.getId());
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getDishUrl(), hashMap, JSONObject.class,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            listener.onAddSuccess(response);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
}
