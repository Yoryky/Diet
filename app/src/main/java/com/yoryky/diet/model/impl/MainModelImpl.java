package com.yoryky.diet.model.impl;

import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.yoryky.diet.model.MainModel;
import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.presenter.listener.OnPutUserListener;
import com.yoryky.diet.presenter.listener.OnUserListener;
import com.yoryky.diet.util.volley.VolleyRequest;
import java.util.HashMap;
import static android.content.ContentValues.TAG;

/**
 * Created by yoryky on 2017/8/28.
 */

public class MainModelImpl implements MainModel {
    @Override
    public void getAllUser(String user_id, final OnUserListener onUserListener) {
        VolleyRequest.newInstance().newGsonRequest("http://192.168.3.102:8081/user/get?user_id="+user_id, User.class,
                new Response.Listener<User>() {
                    @Override
                    public void onResponse(User user) {
                        if(user != null){
                            onUserListener.onSuccess(user);
                        }else{
                            onUserListener.onError();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onUserListener.onError();
                    }
                });
    }

    @Override
    public void getObjectUser(String user_id, final OnUserListener onUserListener) {
        VolleyRequest.newInstance().newGsonRequest("http://192.168.3.102:8081/user/get?user_id=" + user_id, Object.class,
                new Response.Listener<Object>() {
                    @Override
                    public void onResponse(Object response) {
                        if(response != null){
                            Log.d(TAG, "onResponse: "+response);
                        }else {
                            onUserListener.onError();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        onUserListener.onError();
                    }
                });
    }

    @Override
    public void putUser(User user, final OnPutUserListener onPutUserListener) {
        HashMap<String, String> map = new HashMap<>();
        map.put("command","add");
        map.put("name",user.getName());
        map.put("email",user.getEmail());
        VolleyRequest.newInstance().postGsonRequest("http://192.168.3.102:8081/user/",map
        ,Object.class,new Response.Listener<Object>(){
            @Override
            public void onResponse(Object response) {
                if(response != null){
                    onPutUserListener.onSuccess(response.toString());
                }else{
                    onPutUserListener.onSuccess(response.toString());
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                onPutUserListener.onError(error.getMessage());
            }
        });

    }
}
