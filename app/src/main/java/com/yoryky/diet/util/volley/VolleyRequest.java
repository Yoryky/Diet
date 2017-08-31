package com.yoryky.diet.util.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * Created by yoryky on 2015/2/6.
 */
public class VolleyRequest {
    private static RequestQueue mRequestQueue;

    private VolleyRequest() {
    }

    /**
     * @param context ApplicationContext
     */
    public static void buildRequestQueue(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        //... do something
    }

    public static VolleyRequest newInstance() {
        if (mRequestQueue == null) {
            throw new NullPointerException("Call buildRequestQueue method first.");
        }
        //...
        return new VolleyRequest();
    }

    /**
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     * @return
     */
    public <T> GsonRequest<T> newGsonRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                                             Response.ErrorListener errorListener) {
        GsonRequest<T> request = new GsonRequest<T>(url, clazz, listener, errorListener);
        mRequestQueue.add(request);
        return request;
    }


    public <T> GsonRequest<T> postGsonRequest(String url, Map<String, String> params, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        String uri = appendParams(url, params);
        GsonRequest<T> request = new GsonRequest<T>(Request.Method.POST, uri, clazz, listener, errorListener);
        mRequestQueue.add(request);
        return request;
    }


    private String appendParams(String url, Map<String, String> params) {
        String para = "";
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getKey() == "command") {
                url += entry.getValue() + "?";
            } else {
                para += entry.getKey() + "=" + entry.getValue() + "&";
            }
        }
        builder.append(url);
        if(para != ""){
            builder.append(para);
            builder.deleteCharAt(builder.lastIndexOf("&"));
        }
        return builder.toString();
    }
}
