package com.yoryky.diet.presenter.impl;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.model.DishModel;
import com.yoryky.diet.model.entity.Dish;
import com.yoryky.diet.model.impl.DishModelImpl;
import com.yoryky.diet.presenter.DishPresenter;
import com.yoryky.diet.presenter.listener.OnCustomListener;
import com.yoryky.diet.ui.activity.DishDetailActivity;
import com.yoryky.diet.ui.view.DishView;
import com.yoryky.diet.util.HelpUtil;

import java.util.List;


/**
 * Created by yoryky on 2017/8/30.
 */

public class DishPresenterImpl implements DishPresenter, OnCustomListener, DishAdapter.MyItemClickListener {
    private DishView dishView;
    private DishModel dishModel;
    private DishAdapter dishAdapter;

    public DishPresenterImpl(DishView dishView) {
        this.dishView = dishView;
        this.dishModel = new DishModelImpl();
        this.dishAdapter = new DishAdapter(dishView.getContext());
        this.dishAdapter.setShowAddButton(false);
        this.dishAdapter.setOnItemClickListener(this);
        this.dishView.setAdater(dishAdapter);
    }

    @Override
    public void getRandomDish() {
        if (verifyData()) {
            dishView.setNoDataVisible(false);
            this.dishModel.getRandomDish(dishView.getMeatNum(), dishView.getVegNum(), dishView.getSoupNum(), this);
        }
    }

    @Override
    public void onItemClick(View view, List<Dish> dishes, int position) {
        Intent intent = new Intent(dishView.getContext(), DishDetailActivity.class);
        dishView.getContext().startActivity(intent);
    }

    @Override
    public void onAddClick(View view,List<Dish> dishes, int position) {

    }

    @Override
    public void onSuccess(JSONObject json) {
        dishAdapter.getData(json);
    }

    @Override
    public void onError(com.alibaba.fastjson.JSONObject error) {
        HelpUtil.showToast(dishView.getContext(), error.getString("message"));
    }

    private boolean verifyData() {
        if (TextUtils.isEmpty(dishView.getMeatNum())) {
            HelpUtil.showToast(dishView.getContext(), "请输入荤菜数量！");
            return false;
        }
        if (TextUtils.isEmpty(dishView.getVegNum())) {
            HelpUtil.showToast(dishView.getContext(), "请输入素菜数量！");
            return false;
        }
        if (TextUtils.isEmpty(dishView.getSoupNum())) {
            HelpUtil.showToast(dishView.getContext(), "请输入汤数量！");
            return false;
        }
        return true;
    }
}
