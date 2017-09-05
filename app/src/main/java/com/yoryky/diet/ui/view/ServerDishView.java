package com.yoryky.diet.ui.view;

import android.content.Context;

import com.yoryky.diet.adapter.DishAdapter;

/**
 * Created by yoryky on 2017/9/4.
 */

public interface ServerDishView {
    Context getContext();
    void back();
    void setAdapter(DishAdapter dishAdapter);
    String getSearchName();
}
