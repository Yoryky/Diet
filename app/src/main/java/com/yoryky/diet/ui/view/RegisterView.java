package com.yoryky.diet.ui.view;

import android.content.Context;

/**
 * Created by yoryky on 2017/9/1.
 */

public interface RegisterView {
    Context getContext();
    String getUserName();
    String getPassword();
    String getPwdConfirm();
}
