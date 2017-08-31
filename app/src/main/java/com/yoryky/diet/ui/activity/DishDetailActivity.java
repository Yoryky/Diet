package com.yoryky.diet.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yoryky.diet.R;
import com.yoryky.diet.presenter.DishDetailPresenter;
import com.yoryky.diet.presenter.impl.DishDetailPresenterImpl;
import com.yoryky.diet.ui.common.BaseActivity;
import com.yoryky.diet.ui.view.DishDetailView;

/**
 * Created by yoryky on 2017/8/31.
 */

public class DishDetailActivity extends BaseActivity implements DishDetailView, View.OnClickListener {
    private DishDetailPresenter dishDetailPresenter;
    private RelativeLayout rlBack;
    private WebView wvDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_dishdetail);
        this.initViews();
    }

    private void initViews() {
        dishDetailPresenter = new DishDetailPresenterImpl(this);
        rlBack = (RelativeLayout) findViewById(R.id.rl_back);
        wvDetail = (WebView) findViewById(R.id.wvDetail);
        rlBack.setOnClickListener(this);
        loadUrl();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                dishDetailPresenter.back();
                break;
        }
    }

    @Override
    public void back() {
        finish();
    }

    private void loadUrl(){
        wvDetail.getSettings().setJavaScriptEnabled(true);
        final Activity activity = this;
        wvDetail.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int progress) {
                super.onProgressChanged(view, progress);
                activity.setProgress(progress * 1000);
            }
        });
        wvDetail.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity,description,Toast.LENGTH_SHORT).show();
            }
        });
        wvDetail.loadUrl("http://home.meishichina.com/recipe-12378.html");
    }

}
