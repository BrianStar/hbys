package com.wxj.hbys.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.idotools.utils.MetricsUtils;
import com.wxj.hbys.R;
import com.wxj.hbys.activity.AboutActivity;
import com.wxj.hbys.activity.AccountManagerActivity;
import com.wxj.hbys.activity.AddressManagerActivity;
import com.wxj.hbys.activity.CertificationActivity;
import com.wxj.hbys.activity.FeedbackActivity;
import com.wxj.hbys.activity.HelpCenterActivity;
import com.wxj.hbys.activity.LoginActivity;
import com.wxj.hbys.activity.MyAccountActivity;
import com.wxj.hbys.activity.MyHelpActivity;
import com.wxj.hbys.activity.PersonInfoActivity;
import com.wxj.hbys.activity.RegisterActivity;
import com.wxj.hbys.activity.SettingActivity;
import com.wxj.hbys.activity.VerifyIdentityActivity;
import com.wxj.hbys.activity.WatchAdActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wuxiaojun on 2017/1/8.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private View contentView;

    @BindView(R.id.ll_height)
    LinearLayout ll_login_height;
    @BindView(R.id.rl_user_info)
    RelativeLayout rl_user_info;
    @BindView(R.id.ll_logined)
    LinearLayout ll_logined;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentView == null) {
            contentView = inflater.inflate(R.layout.fragment_my, null);
        }
        ButterKnife.bind(this, contentView);
        return contentView;
    }


    @OnClick({R.id.rl_user_info, R.id.iv_setting, R.id.tv_login, R.id.tv_register, R.id.tv_account,
            R.id.tv_my_help, R.id.tv_my_reward, R.id.tv_my_vote, R.id.tv_my_collection, R.id.tv_share})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.rl_user_info:
                //需要判断是否已登陆
                startActivity(new Intent(mContext, AccountManagerActivity.class));

                break;
            case R.id.iv_setting:
                startActivity(new Intent(mContext, SettingActivity.class));

                break;

            case R.id.tv_login:
                //点击登陆完成，需要动态改变布局的高度
                if (ll_logined.getVisibility() == View.GONE) {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) ll_login_height.getLayoutParams();
                    lp.height = MetricsUtils.dipToPx(210);
                    ll_login_height.requestLayout();
                    ll_logined.setVisibility(View.VISIBLE);
                }


                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));

                break;
            case R.id.tv_account:
                startActivity(new Intent(mContext, MyAccountActivity.class));

                break;
            case R.id.tv_my_help:
                startActivity(new Intent(mContext,MyHelpActivity.class));

                break;
            case R.id.tv_my_reward:

                break;
            case R.id.tv_my_vote:

                break;
            case R.id.tv_my_collection:

                break;
            case R.id.tv_share:

                break;
        }
    }


}