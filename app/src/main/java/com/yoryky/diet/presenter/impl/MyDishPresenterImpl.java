package com.yoryky.diet.presenter.impl;

import android.content.Intent;
import android.view.View;
import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.model.MyDishModel;
import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.model.impl.MyDishModelImpl;
import com.yoryky.diet.presenter.MyDishPresenter;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.activity.ServerDishActivity;
import com.yoryky.diet.ui.view.MyDishView;
import com.yoryky.diet.util.ConstantUtil;

import java.util.List;

/**
 * Created by caicai on 2017/9/3.
 */

public class MyDishPresenterImpl implements MyDishPresenter,OnCustomListener,DishAdapter.MyItemClickListener {
    private MyDishView dishListView;
    private MyDishModel dishListModel;
    private DishAdapter dishAdapter;
    public MyDishPresenterImpl(MyDishView dishListView){
        this.dishListView = dishListView;
        this.dishListModel = new MyDishModelImpl();
        this.dishAdapter = new DishAdapter(dishListView.getContext());
        this.dishAdapter.setOnItemClickListener(this);
        this.dishListView.setAdaprer(dishAdapter);
        this.getData();
    }

    @Override
    public void startServerDishActivity() {
        Intent intent = new Intent(dishListView.getContext(), ServerDishActivity.class);
        dishListView.getActivity().startActivityForResult(intent, ConstantUtil.SERVER_DISH_BACK);
    }

    @Override
    public void getData() {
        dishListView.setNoDataVisible(false);
        dishListModel.getDishes(this);
    }

    @Override
    public void refreshData() {
        dishListModel.getDishes(this);
    }

    @Override
    public void back() {
        dishListView.back();
    }

    @Override
    public void onItemClick(View view, List<Dish> dishes, int position) {

    }

    @Override
    public void onAddClick(View view,List<Dish> dishes, int position) {

    }

    @Override
    public void onSuccess(JSONObject result) {
        dishAdapter.getData(result);
    }

    @Override
    public void onError(JSONObject error) {

    }
}
