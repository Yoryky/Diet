package com.yoryky.diet.model;

import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.presenter.listener.OnAddUserDishListener;
import com.yoryky.diet.presenter.listener.OnCustomListener;

/**
 * Created by yoryky on 2017/9/4.
 */

public interface ServerDishModel {
    void getSearchData(String searchName,int pageIndex,int pageSize, OnCustomListener listener);
    void addUserDishData(Dish dish, OnAddUserDishListener listener);
}
