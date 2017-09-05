package com.yoryky.diet.ui.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yoryky.diet.R;
import com.yoryky.diet.event.MeUpdateEvent;
import com.yoryky.diet.presenter.MePresenter;
import com.yoryky.diet.presenter.impl.MePresenterImpl;
import com.yoryky.diet.ui.view.MeView;
import com.yoryky.diet.util.HelpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by yoryky on 2017/9/1.
 */

public class MeFragment extends Fragment implements MeView,View.OnClickListener{
    private MePresenter mePresenter;
    private LinearLayout llEditInfo;
    private LinearLayout llEditDish;
    private LinearLayout llSetting;
    private TextView tvUser;
    private View meLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        meLayout = inflater.inflate(R.layout.fragment_me,container,false);
        this.initViews();
        return meLayout;
    }

    private void initViews(){
        mePresenter = new MePresenterImpl(this);
        llEditInfo = (LinearLayout)meLayout.findViewById(R.id.ll_edit_info);
        llEditDish = (LinearLayout)meLayout.findViewById(R.id.ll_dish);
        llSetting = (LinearLayout)meLayout.findViewById(R.id.ll_setting);
        tvUser = (TextView)meLayout.findViewById(R.id.tv_user);
        llEditInfo.setOnClickListener(this);
        llEditDish.setOnClickListener(this);
        llSetting.setOnClickListener(this);
        mePresenter.setUserName();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_edit_info:
                break;
            case R.id.ll_dish:
                mePresenter.startDishListActivity();
                break;
            case R.id.ll_setting:
                mePresenter.startSettingActivity();
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(MeUpdateEvent event){
        String message = event.getMessage();
        JSONObject json = JSON.parseObject(message);
        if(json.getString("name").equals("me")){
            setUserName(json.getJSONObject("data").getString("user_name"));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void setUserName(String name) {
        tvUser.setText(name);
    }
}
