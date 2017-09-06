package com.yoryky.diet.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.yoryky.diet.R;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.presenter.ServerDishPresenter;
import com.yoryky.diet.presenter.impl.ServerDishPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.ServerDishView;

/**
 * Created by yoryky on 2017/9/4.
 */

public class ServerDishActivity extends BaseActivity implements ServerDishView,View.OnClickListener{
    private RelativeLayout rlBack;
    private EditText etDishName;
    private Button btnSearch;
    private RecyclerView rvServerDish;
    private ServerDishPresenter serverDishPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serverdish);
        this.initViews();
    }

    private void initViews(){
        rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        etDishName = (EditText)findViewById(R.id.et_dish_name);
        btnSearch = (Button)findViewById(R.id.btn_search);
        rvServerDish = (RecyclerView)findViewById(R.id.rv_server_dish);
        serverDishPresenter = new ServerDishPresenterImpl(this);
        btnSearch.setOnClickListener(this);
        rlBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                serverDishPresenter.back();
                break;
            case R.id.btn_search:
                serverDishPresenter.getDishData();
                break;
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void back() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void setAdapter(DishAdapter dishAdapter,LinearLayoutManager manager) {
        rvServerDish.setLayoutManager(manager);
        rvServerDish.setAdapter(dishAdapter);
    }

    @Override
    public String getSearchName() {
        return etDishName.getText().toString();
    }

    @Override
    public void setOnScrollListener(RecyclerView.OnScrollListener listener) {
        rvServerDish.setOnScrollListener(listener);
    }
}
