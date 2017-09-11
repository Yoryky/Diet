package com.yoryky.diet.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yoryky.diet.adapter.DishAdapter;

/**
 * Created by yoryky on 2017/9/4.
 */

public interface ServerDishView {
    Context getContext();
    void back();
    void setAdapter(DishAdapter dishAdapter,LinearLayoutManager manager);
    String getSearchName();
    void setOnScrollListener(RecyclerView.OnScrollListener listener);
    void setOnRefresh();
    void stopRefresh();
}
