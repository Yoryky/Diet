package com.yoryky.diet.presenter.listener;

import com.yoryky.diet.model.entity.User;

/**
 * Created by yoryky on 2017/8/29.
 */

public interface OnPutUserListener {
    void onSuccess(String result);

    void onError(String result);
}
