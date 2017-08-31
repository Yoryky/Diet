package com.yoryky.diet.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yoryky.diet.R;
import com.yoryky.diet.presenter.MainPresenter;
import com.yoryky.diet.presenter.impl.MainPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.MainView;

/**
 * Created by yoryky on 2017/8/28.
 */

public class MainActivity extends BaseActivity implements MainView,View.OnClickListener {
    private Button btnUser;
    private EditText etUserId;
    private EditText etName;
    private EditText etEmail;
    private Button btnSubmit;
    private Button btnDish;
    private MainPresenter userPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initViews();
    }

    private void initViews(){
        btnUser = (Button)findViewById(R.id.btn_user);
        etUserId = (EditText)findViewById(R.id.et_user_id);
        etName = (EditText)findViewById(R.id.et_name);
        etEmail = (EditText)findViewById(R.id.et_email);
        btnSubmit = (Button)findViewById(R.id.btn_submit);
        btnDish = (Button)findViewById(R.id.btn_dish);
        userPresenter = new MainPresenterImpl(this);
        btnUser.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
        btnDish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_user:
                userPresenter.getUser(etUserId.getText().toString());
                break;
            case R.id.btn_submit:
                userPresenter.putUser();
                break;
            case R.id.btn_dish:
                userPresenter.startDishActivity();
                break;
        }
    }

    @Override
    public String getName(){
        return etName.getText().toString();
    }

    @Override
    public String getEmail(){
        return etEmail.getText().toString();
    }

}
