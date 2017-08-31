package com.yoryky.diet.presenter;

import com.yoryky.diet.model.entity.User;
import com.yoryky.diet.presenter.listener.OnUserListener;

/**
 * Created by yoryky on 2017/8/29.
 */

public interface MainPresenter {
    public void getUser(String user_id);
    public void putUser();
    public void startDishActivity();
}
