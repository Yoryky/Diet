package com.yoryky.diet.presenter.listener;

import com.yoryky.diet.model.entity.User;

import java.util.List;

/**
 * Created by yoryky on 2017/8/29.
 */

public interface OnUserListener {
    void onSuccess(User user);

    void onError();
}
