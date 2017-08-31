package com.yoryky.diet.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by yoryky on 2017/8/31.
 */

public class HelpUtil {
    public static void showToast(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
    }
}
