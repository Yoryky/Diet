package com.yoryky.diet.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yoryky.diet.R;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.presenter.DishPresenter;
import com.yoryky.diet.presenter.impl.DishPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.DishView;

/**
 * Created by yoryky on 2017/8/30.
 */

public class DishActivity extends BaseActivity implements DishView, View.OnClickListener{
    private EditText etPersonNum;
    private EditText etMeatNum;
    private EditText etVegNum;
    private EditText etSoupNum;
    private TextView tvNoData;
    private RelativeLayout rlBack;
    private RelativeLayout rlSubmit;
    private RecyclerView rvDish;
    private DishPresenter dishPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        this.initViews();
    }

    private void initViews(){
        etPersonNum = (EditText)findViewById(R.id.et_person_num);
        etMeatNum = (EditText)findViewById(R.id.et_meat_num);
        etVegNum = (EditText)findViewById(R.id.et_veg_num);
        etSoupNum = (EditText)findViewById(R.id.et_soup_num);
        tvNoData = (TextView)findViewById(R.id.tv_no_data);
        rvDish = (RecyclerView)findViewById(R.id.rv_dish);
        dishPresenter = new DishPresenterImpl(this);
        rlBack = (RelativeLayout)findViewById(R.id.rl_back);
        rlSubmit = (RelativeLayout) findViewById(R.id.rl_submit);
        rlBack.setOnClickListener(this);
        rlSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_back:
                dishPresenter.back();
                break;
            case R.id.rl_submit:
                dishPresenter.getRandomDish();
                break;
        }
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public String getPersonNum(){
        return etPersonNum.getText().toString();
    }

    public String getMeatNum(){
        return etMeatNum.getText().toString();
    }

    public String getVegNum(){
        return etVegNum.getText().toString();
    }

    public String getSoupNum(){
        return etSoupNum.getText().toString();
    }

    @Override
    public void setNoDataVisible(boolean visible) {
        tvNoData.setVisibility(visible ? View.VISIBLE:View.GONE);
    }

    @Override
    public void setAdater(DishAdapter dishAdapter) {
        rvDish.setLayoutManager(new LinearLayoutManager(this));
        rvDish.setAdapter(dishAdapter);
    }
}
