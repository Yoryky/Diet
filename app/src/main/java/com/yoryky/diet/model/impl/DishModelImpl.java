package com.yoryky.diet.model.impl;

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
        hashMap.put("meat_num",meat);
        hashMap.put("veg_num",veg);
        hashMap.put("soup_num",soup);
        VolleyRequest.newInstance().postGsonRequest(MyApplication.getContext().getDishUrl(), hashMap, Object.class,
                new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object response) {
                        if(response != null){
                            onCustomListener.onSuccess(response.toString());
                        }else{
                            onCustomListener.onError(response.toString());
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onCustomListener.onError(error.getMessage());
                    }
                });
    }
}
