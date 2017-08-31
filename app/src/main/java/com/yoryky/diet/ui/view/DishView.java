package com.yoryky.diet.ui.view;

import android.content.Context;

import com.yoryky.diet.adapter.DishAdapter;

/**
 * Created by yoryky on 2017/8/30.
 */

public interface DishView {

    public String getPersonNum();

    public String getMeatNum();

    public String getVegNum();

    public String getSoupNum();

    public void setAdater(DishAdapter dishAdapter);

    public void back();

    public Context getContext();

    public void setNoDataVisible(boolean visible);
}
