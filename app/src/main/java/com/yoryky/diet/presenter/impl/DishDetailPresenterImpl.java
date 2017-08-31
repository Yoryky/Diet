package com.yoryky.diet.presenter.impl;

import com.yoryky.diet.presenter.DishDetailPresenter;
import com.yoryky.diet.ui.view.DishDetailView;

/**
 * Created by yoryky on 2017/8/31.
 */

public class DishDetailPresenterImpl implements DishDetailPresenter {
    private DishDetailView dishDetailView;
    public DishDetailPresenterImpl(DishDetailView dishDetailView){
        this.dishDetailView = dishDetailView;
    }
    @Override
    public void back() {
        dishDetailView.back();
    }
}
