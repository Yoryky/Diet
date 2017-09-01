package com.yoryky.diet.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yoryky.diet.R;
import com.yoryky.diet.adapter.DishAdapter;
import com.yoryky.diet.presenter.DishPresenter;
import com.yoryky.diet.presenter.impl.DishPresenterImpl;
import com.yoryky.diet.ui.view.DishView;

/**
 * Created by yoryky on 2017/9/1.
 */

public class DishFragment extends Fragment implements DishView,View.OnClickListener {
    private EditText etPersonNum;
    private EditText etMeatNum;
    private EditText etVegNum;
    private EditText etSoupNum;
    private TextView tvNoData;
    private RelativeLayout rlBack;
    private RelativeLayout rlSubmit;
    private RecyclerView rvDish;
    private DishPresenter dishPresenter;
    private View dishLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        dishLayout = inflater.inflate(R.layout.fragment_dish,container,false);
        this.initViews();
        return dishLayout;
    }

    private void initViews(){
        etPersonNum = (EditText)dishLayout.findViewById(R.id.et_person_num);
        etMeatNum = (EditText)dishLayout.findViewById(R.id.et_meat_num);
        etVegNum = (EditText)dishLayout.findViewById(R.id.et_veg_num);
        etSoupNum = (EditText)dishLayout.findViewById(R.id.et_soup_num);
        tvNoData = (TextView)dishLayout.findViewById(R.id.tv_no_data);
        rvDish = (RecyclerView)dishLayout.findViewById(R.id.rv_dish);
        dishPresenter = new DishPresenterImpl(this);
        rlBack = (RelativeLayout)dishLayout.findViewById(R.id.rl_back);
        rlSubmit = (RelativeLayout) dishLayout.findViewById(R.id.rl_submit);
        rlBack.setOnClickListener(this);
        rlSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_submit:
                dishPresenter.getRandomDish();
                break;
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
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
        rvDish.setLayoutManager(new LinearLayoutManager(getContext()));
        rvDish.setAdapter(dishAdapter);
    }
}
