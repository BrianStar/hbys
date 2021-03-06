package com.help.reward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.help.reward.App;
import com.help.reward.R;
import com.help.reward.bean.Response.LoginResponse;
import com.help.reward.bean.Response.RegisterResponse;
import com.help.reward.bean.Response.VerificationCodeResponse;
import com.help.reward.network.PersonalNetwork;
import com.help.reward.network.base.BaseSubscriber;
import com.help.reward.rxbus.RxBus;
import com.help.reward.utils.ActivitySlideAnim;
import com.help.reward.utils.Constant;
import com.help.reward.utils.CountDownTimeUtils;
import com.help.reward.utils.ValidateUtil;
import com.help.reward.view.MyProcessDialog;
import com.idotools.utils.LogUtils;
import com.idotools.utils.ToastUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wuxiaojun on 2017/1/8.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_title_back)
    ImageView iv_title_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_title_right)
    TextView tv_title_right;

    @BindView(R.id.et_phone_number)
    EditText et_phone_number; // 手机号
    @BindView(R.id.tv_code)
    TextView tv_code; // 计时器
    @BindView(R.id.et_code)
    EditText et_code; // 验证码

    @BindView(R.id.et_pwd)
    EditText et_pwd; // 密码
    @BindView(R.id.et_pwd_custom)
    EditText et_pwd_custom; // 确认密码
    @BindView(R.id.et_invitation_code)
    EditText et_invitation_code; // 邀请code
    @BindView(R.id.cb_agreement)
    CheckBox cb_agreement; // 复选框

    private CountDownTimeUtils mTimer;
    public String verificationCode; // 请求到的code

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        initTimer();

    }

    private void initTimer() {
        mTimer = new CountDownTimeUtils();
        mTimer.setOnCountDownTimeListener(new CountDownTimeUtils.OnCountDownTimeListener() {
            @Override
            public void onTick(long millisUntilFinished) { // 计时开始
                tv_code.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() { // 计时结束
                tv_code.setClickable(true);
                tv_code.setText(R.string.string_recapture);
            }
        });
    }

    private void initView() {
        tv_title.setText(R.string.string_register_title);
        tv_title_right.setVisibility(View.GONE);
    }

    @OnClick({R.id.iv_title_back, R.id.tv_code, R.id.btn_register})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_title_back:
                finish();
                ActivitySlideAnim.slideOutAnim(RegisterActivity.this);

                break;
            case R.id.tv_code: // 点击验证code
                String registerUserName = et_phone_number.getText().toString().trim();
                if (!TextUtils.isEmpty(registerUserName)) {
                    if (ValidateUtil.isMobile(registerUserName)) {
                        LogUtils.e("点击获取code");
                        tv_code.setClickable(false);
                        requestVerificationCode(registerUserName);
                    } else {
                        ToastUtils.show(mContext, "手机号格式不正确");
                    }
                } else {
                    ToastUtils.show(mContext, "请输入手机号");
                }

                break;
            case R.id.btn_register: // 注册
                register();

                break;
        }
    }

    /**
     * 获取code
     */
    private void requestVerificationCode(String registerUserName) {
//        MyProcessDialog.showDialog(mContext);
        PersonalNetwork
                .getLoginApi()
                .getVerificationCodeBean(registerUserName, "1")
                .subscribeOn(Schedulers.io()) // 请求放在io线程中
                .observeOn(AndroidSchedulers.mainThread()) // 请求结果放在主线程中
                .subscribe(new BaseSubscriber<VerificationCodeResponse>() {
                    @Override
                    public void onError(Throwable e) {
//                        MyProcessDialog.closeDialog();
                        e.printStackTrace();
                        if (e instanceof UnknownHostException) {
                            ToastUtils.show(mContext, "请求到错误服务器");
                        } else if (e instanceof SocketTimeoutException) {
                            ToastUtils.show(mContext, "请求超时");
                        }
                        tv_code.setClickable(true);
                    }

                    @Override
                    public void onNext(VerificationCodeResponse res) {
//                        MyProcessDialog.closeDialog();
                        if (res.code == 200) { // 获取验证code成功
                            mTimer.start();
                            LogUtils.e("获取code成功" + res.data.sms_time);
                            verificationCode = res.data.sms_time;
                        } else {
                            ToastUtils.show(mContext, res.msg);
                            tv_code.setClickable(true);
                        }
                    }
                });
    }

    /***
     * 点击注册
     */
    private void register() {
        String registerUserName = et_phone_number.getText().toString().trim();
        String registerPwd = et_pwd.getText().toString().trim();
        String registerPwdAgain = et_pwd_custom.getText().toString().trim();
        String registerCode = et_code.getText().toString().trim();
        String registerInvitationCode = et_invitation_code.getText().toString().trim(); // 邀请code

        if (!TextUtils.isEmpty(registerUserName)) {
            if (ValidateUtil.isMobile(registerUserName)) {
                if (!TextUtils.isEmpty(registerCode)) {
                    if (!TextUtils.isEmpty(registerPwd)) {
                        if (!TextUtils.isEmpty(registerPwdAgain)) {
                            if (registerPwd.equals(registerPwdAgain)) {
                                if (cb_agreement.isChecked()) {
                                    requestRegister(registerUserName, registerPwd, registerCode, registerInvitationCode);
                                } else {
                                    ToastUtils.show(mContext, "请选中用户说明");
                                }
                            } else {
                                ToastUtils.show(mContext, "密码不一致，请重新输入");
                            }
                        } else {
                            ToastUtils.show(mContext, "请再次输入密码");
                        }
                    } else {
                        ToastUtils.show(mContext, "请输入密码");
                    }
                } else {
                    ToastUtils.show(mContext, "请输入验证码");
                }
            } else {
                ToastUtils.show(mContext, "手机号格式不正确");
            }
        } else {
            ToastUtils.show(mContext, "请输入手机号");
        }
    }

    private void requestRegister(String registerUserName, String registerPwd, String registerCode, String registerInvitationCode) {
        MyProcessDialog.showDialog(mContext);
        PersonalNetwork
                .getLoginApi()
                .getRegisterBean(registerUserName,registerCode,registerPwd, Constant.PLATFORM_CLIENT)
                .subscribeOn(Schedulers.io()) // 请求放在io线程中
                .observeOn(AndroidSchedulers.mainThread()) // 请求结果放在主线程中
                .subscribe(new BaseSubscriber<RegisterResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        MyProcessDialog.closeDialog();
                        e.printStackTrace();
                        if (e instanceof UnknownHostException) {
                            ToastUtils.show(mContext, "请求到错误服务器");
                        } else if (e instanceof SocketTimeoutException) {
                            ToastUtils.show(mContext, "请求超时");
                        }
                    }

                    @Override
                    public void onNext(RegisterResponse res) {
                        MyProcessDialog.closeDialog();
                        if (res.code == 200) { // 获取验证code成功
                            App.APP_CLIENT_KEY = res.data.key;
                            LogUtils.e("注册成功。。。"+res.data.username);
                        } else {
                            ToastUtils.show(mContext, res.msg);
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        super.onDestroy();
    }
}
