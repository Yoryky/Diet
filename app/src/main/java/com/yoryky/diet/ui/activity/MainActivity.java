package com.yoryky.diet.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoryky.diet.R;
import com.yoryky.diet.presenter.MainPresenter;
import com.yoryky.diet.presenter.impl.MainPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.fragment.DishFragment;
import com.yoryky.diet.ui.fragment.HomeFragment;
import com.yoryky.diet.ui.fragment.MeFragment;
import com.yoryky.diet.ui.view.MainView;

/**
 * Created by yoryky on 2017/8/28.
 */

public class MainActivity extends BaseActivity implements MainView, View.OnClickListener {

    private MainPresenter mainPresenter;
    private HomeFragment homeFragment;
    private DishFragment dishFragment;
    private MeFragment meFragment;
    private View homeLayout;
    private View dishLayout;
    private View meLayout;
    private ImageView ivHome;
    private ImageView ivDish;
    private ImageView ivMe;
    private TextView tvHome;
    private TextView tvDish;
    private TextView tvMe;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        this.initViews();

    }

    private void initViews() {
        fragmentManager = getFragmentManager();
        homeLayout = findViewById(R.id.ll_home);
        dishLayout = findViewById(R.id.ll_dish);
        meLayout = findViewById(R.id.ll_me);
        ivHome = (ImageView)findViewById(R.id.iv_home);
        ivDish = (ImageView)findViewById(R.id.iv_dish);
        ivMe = (ImageView)findViewById(R.id.iv_me);
        tvHome = (TextView)findViewById(R.id.tv_home);
        tvDish = (TextView)findViewById(R.id.tv_dish);
        tvMe = (TextView)findViewById(R.id.tv_me);
        homeLayout.setOnClickListener(this);
        dishLayout.setOnClickListener(this);
        meLayout.setOnClickListener(this);
        setTabSelection(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_home:
                setTabSelection(0);
                break;
            case R.id.ll_dish:
                setTabSelection(1);
                break;
            case R.id.ll_me:
                setTabSelection(2);
                break;
        }
    }

    private void setTabSelection(int index){
        clearSelection();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index){
            case 0:
                ivHome.setImageResource(R.mipmap.home_pressed);
                tvHome.setTextColor(getResources().getColor(R.color.light_blue));
                homeLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white_smoke));
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_content,homeFragment);
                }else{
                    transaction.show(homeFragment);
                }
                break;
            case 1:
                ivDish.setImageResource(R.mipmap.dish_pressed);
                tvDish.setTextColor(getResources().getColor(R.color.light_blue));
                dishLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white_smoke));
                if(dishFragment == null){
                    dishFragment = new DishFragment();
                    transaction.add(R.id.fl_content,dishFragment);
                }else{
                    transaction.show(dishFragment);
                }
                break;
            case 2:
                ivMe.setImageResource(R.mipmap.me_pressed);
                tvMe.setTextColor(getResources().getColor(R.color.light_blue));
                meLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white_smoke));
                if(meFragment == null){
                    meFragment = new MeFragment();
                    transaction.add(R.id.fl_content,meFragment);
                }else{
                    transaction.show(meFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void clearSelection(){
        ivHome.setImageResource(R.mipmap.home_normal);
        tvHome.setTextColor(getResources().getColor(R.color.black));
        homeLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        ivDish.setImageResource(R.mipmap.dish_normal);
        tvDish.setTextColor(getResources().getColor(R.color.black));
        dishLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        ivMe.setImageResource(R.mipmap.me_normal);
        meLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.white));
        tvMe.setTextColor(getResources().getColor(R.color.black));
    }

    private void hideFragments(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(dishFragment != null){
            transaction.hide(dishFragment);
        }
        if(meFragment != null){
            transaction.hide(meFragment);
        }
    }

}
