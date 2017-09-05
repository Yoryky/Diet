package com.yoryky.diet.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yoryky.diet.R;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.presenter.MyDishPresenter;
import com.yoryky.diet.presenter.impl.MyDishPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.MyDishView;

/**
 * Created by caicai on 2017/9/3.
 */

public class MyDishActivity extends BaseActivity implements MyDishView, View.OnClickListener {
    private RelativeLayout rlBack;
    private RelativeLayout rlAdd;
    private TextView tvNoData;
    private RecyclerView rvDish;
    private MyDishPresenter dishListPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydish);
        this.initViews();
    }

    private void initViews(){
        rlBack = (RelativeLayout)findViewById(R.id.rl_back);
        rlAdd = (RelativeLayout)findViewById(R.id.rl_add);
        tvNoData = (TextView)findViewById(R.id.tv_no_data);
        rvDish = (RecyclerView)findViewById(R.id.rv_dish);
        dishListPresenter = new MyDishPresenterImpl(this);
        rlBack.setOnClickListener(this);
        rlAdd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                dishListPresenter.back();
                break;
            case R.id.rl_add:
                dishListPresenter.startServerDishActivity();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            dishListPresenter.refreshData();
        }
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void setNoDataVisible(boolean visible) {
        tvNoData.setVisibility(visible? View.VISIBLE : View.GONE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void setAdaprer(DishAdapter dishAdapter) {
        rvDish.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDish.setAdapter(dishAdapter);
    }
}
