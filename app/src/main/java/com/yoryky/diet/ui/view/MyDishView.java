package com.yoryky.diet.ui.view;

import android.app.Activity;
import android.content.Context;

import com.yoryky.diet.adapter.DishAdapter;

/**
 * Created by caicai on 2017/9/3.
 */

public interface MyDishView {
    void back();
    void setNoDataVisible(boolean visible);
    Context getContext();
    Activity getActivity();
    void setAdaprer(DishAdapter dishAdapter);
}
