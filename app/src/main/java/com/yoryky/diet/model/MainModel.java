package com.yoryky.diet.model;

import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.presenter.listener.OnPutUserListener;
import com.yoryky.diet.presenter.listener.OnUserListener;

/**
 * Created by yoryky on 2017/8/28.
 */

public interface MainModel {
    public void getAllUser(String user_id, OnUserListener onUserListener);
    public void getObjectUser(String user_id,OnUserListener onUserListener);

    public void putUser(User user,OnPutUserListener onPutUserListener);
}
