package com.wxj.hbys.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wxj.hbys.R;
import com.wxj.hbys.activity.ShopInfoActivity;

/**
 * Created by MXY on 2017/2/26.
 */

public class GoodImgInfoFragment extends BaseFragment{

    private View contentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_goodinfo_img, null);
        }
        initView();
        return contentView;
    }

    private void initView() {

    }
}