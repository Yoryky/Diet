package com.yoryky.diet.model;

import com.yoryky.diet.presenter.listener.OnCustomListener;

/**
 * Created by yoryky on 2017/8/30.
 */

public interface DishModel {
    public void getRandomDish(String meat, String veg, String soup, OnCustomListener onCustomListener);
}
