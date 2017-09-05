package com.yoryky.diet.util;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

/**
 * Created by yoryky on 2017/8/31.
 */

public class HelpUtil {
    public static void showToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }

    public static String getEventBusData(String name,HashMap<String,String> data){
        String strData = JSON.toJSONString(data);
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("data",strData);
        hashMap.put("name",name);
        return JSON.toJSONString(hashMap);
    }
}
