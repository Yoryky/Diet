package com.yoryky.diet.ui.view;

import android.content.Context;

/**
 * Created by yoryky on 2017/9/1.
 */

public interface LoginView {
    Context getContext();
    String getUserName();
    String getPassword();
    void back();
}
