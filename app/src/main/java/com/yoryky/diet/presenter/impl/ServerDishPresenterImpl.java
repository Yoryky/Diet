package com.yoryky.diet.presenter.impl;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.model.ServerDishModel;
import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.model.impl.ServerDishModelImpl;
import com.yoryky.diet.presenter.ServerDishPresenter;
import com.yoryky.diet.presenter.listener.OnAddUserDishListener;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.view.ServerDishView;
import com.yoryky.diet.util.HelpUtil;

import java.util.List;

/**
 * Created by yoryky on 2017/9/4.
 */

public class ServerDishPresenterImpl implements ServerDishPresenter, OnCustomListener, DishAdapter.MyItemClickListener, OnAddUserDishListener {
    private ServerDishView serverDishView;
    private ServerDishModel serverDishModel;
    private DishAdapter dishAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;


    public ServerDishPresenterImpl(ServerDishView serverDishView) {
        this.serverDishView = serverDishView;
        this.serverDishModel = new ServerDishModelImpl();
        this.dishAdapter = new DishAdapter(serverDishView.getContext());
        this.dishAdapter.setShowAddButton(true);
        this.dishAdapter.setSplitPage(true);
        this.dishAdapter.setOnItemClickListener(this);
        linearLayoutManager = new LinearLayoutManager(serverDishView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.serverDishView.setAdapter(dishAdapter, linearLayoutManager);
        this.serverDishView.setOnRefresh();
        this.serverDishView.setOnScrollListener(new ServerDishOnScrollListener());
        this.getDishData();
    }

    @Override
    public void onItemClick(View view, List<Dish> dishes, int position) {

    }

    @Override
    public void onAddClick(View view, List<Dish> dishes, int position) {
        serverDishModel.addUserDishData(dishes.get(position), this);
    }

    @Override
    public void back() {
        serverDishView.back();
    }

    @Override
    public void getDishData() {
        int pageIndex = dishAdapter.getPageIndex();
        int pageSize = dishAdapter.getPageSize();
        serverDishModel.getSearchData(serverDishView.getSearchName(),pageIndex, pageSize, this);
    }

    @Override
    public void onRefresh() {
        dishAdapter.setRefresh(true);
        getDishData();
    }

    @Override
    public void onSuccess(JSONObject result) {
        if(dishAdapter.isRefresh()){
            stopRefresh();
        }
        dishAdapter.getData(result);
    }

    @Override
    public void onError(JSONObject error) {

    }

    @Override
    public void onAddSuccess(JSONObject result) {
        if (result.getIntValue("code") == 0) {
            HelpUtil.showToast(serverDishView.getContext(), "添加成功");
        } else {
            HelpUtil.showToast(serverDishView.getContext(), result.getString("message"));
        }
    }

    @Override
    public void onAddError(JSONObject error) {

    }


    public class ServerDishOnScrollListener extends RecyclerView.OnScrollListener {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == dishAdapter.getItemCount()) {
                if (!dishAdapter.getNoMoreData()) {
                    dishAdapter.changeMoreStatus(DishAdapter.LOADING_MORE);
                    getDishData();
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();

        }
    }

    @Override
    public void stopRefresh() {
        serverDishView.stopRefresh();
    }
}
