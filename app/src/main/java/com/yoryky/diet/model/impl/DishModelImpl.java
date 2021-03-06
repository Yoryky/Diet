package com.yoryky.diet.model.impl;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yoryky.diet.app.MyApplication;
import com.yoryky.diet.model.DishModel;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.view.DishView;
import com.yoryky.diet.util.volley.VolleyRequest;

import java.util.HashMap;

/**
 * Created by yoryky on 2017/8/30.
 */

public class DishModelImpl implements DishModel {

    @Override
    public void getRandomDish(String meat, String veg, String soup, final OnCustomListener onCustomListener) {
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("command","random");
        hashMap.put("user_id",MyApplication.getContext().getUser().getId());
        hashMap.put("meat_num",meat);
        hashMap.put("veg_num",veg);
        hashMap.put("soup_num",soup);
        hashMap.put("from_server","0");
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getDishUrl(), hashMap, JSONObject.class,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response != null){
                            onCustomListener.onSuccess(response);
                        }else{
                            onCustomListener.onError(response);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
    }
}
