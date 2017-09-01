package com.yoryky.diet.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoryky.diet.R;

/**
 * Created by yoryky on 2017/9/1.
 */

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View homeLayout = inflater.inflate(R.layout.fragment_home,container,false);
        return homeLayout;
    }
}
