package com.yoryky.diet.presenter.impl;

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

public class ServerDishPresenterImpl implements ServerDishPresenter,OnCustomListener,DishAdapter.MyItemClickListener,OnAddUserDishListener{
    private ServerDishView serverDishView;
    private ServerDishModel serverDishModel;
    private DishAdapter dishAdapter;
    public ServerDishPresenterImpl(ServerDishView serverDishView){
        this.serverDishView = serverDishView;
        this.serverDishModel = new ServerDishModelImpl();
        this.dishAdapter = new DishAdapter(serverDishView.getContext());
        this.dishAdapter.setShowAddButton(true);
        this.dishAdapter.setOnItemClickListener(this);
        this.serverDishView.setAdapter(dishAdapter);
        this.getDishData();
    }

    @Override
    public void onItemClick(View view,List<Dish> dishes, int position) {

    }

    @Override
    public void onAddClick(View view, List<Dish> dishes, int position) {
        serverDishModel.addUserDishData(dishes.get(position),this);
    }

    @Override
    public void back() {
        serverDishView.back();
    }

    @Override
    public void getDishData() {
        serverDishModel.getSearchData(serverDishView.getSearchName(),this);
    }

    @Override
    public void onSuccess(JSONObject result) {
        dishAdapter.getData(result);
    }

    @Override
    public void onError(JSONObject error) {

    }

    @Override
    public void onAddSuccess(JSONObject result) {
        if(result.getIntValue("code") == 0){
            HelpUtil.showToast(serverDishView.getContext(),"添加成功");
        }else{
            HelpUtil.showToast(serverDishView.getContext(),result.getString("message"));
        }
    }

    @Override
    public void onAddError(JSONObject error) {

    }
}
