package com.example.b2c.activity.seller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.ForgetPswActivity;
import com.example.b2c.activity.MainActivity;
import com.example.b2c.activity.RegisterActivity;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.config.Configs;
import com.example.b2c.helper.SPHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.helper.UIHelper;
import com.example.b2c.helper.UserHelper;
import com.example.b2c.net.listener.LoginListener;
import com.example.b2c.net.util.DateUtils;
import com.example.b2c.net.util.Md5Encrypt;
import com.example.b2c.widget.EditTextWithDelete;
import com.example.b2c.widget.RefreshLayout;
import com.example.b2c.widget.SimpleTextWatcher;

import butterknife.OnClick;


/**
 * 用途：卖家登录
 * Created by milk on 16/10/24.
 * 邮箱：649444395@qq.com
 */

public class SellerLoginActivity extends TempBaseActivity {
    private EditTextWithDelete mUsername;
    private EditTextWithDelete mTvPassword;
    private Button mBtnLogin;
    private TextView mTvLoad;
    private TextView mTvForgetPassword;
    private RelativeLayout rl_seller_jizhu;
    private ImageView iv_seller_password1;
    private ImageView iv_seller_password2;
    private TextView tv_seller_jizhumima;
    private boolean isPassword=true;
    private String jieguo;

    @Override
    public int getRootId() {
        return R.layout.activity_seller_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //登录页面清空所有用户信息
        UserHelper.clearUserLocalAll();
        setTitle(mTranslatesString.getCommon_shopLogin());
        initView();
    }

    public void initView() {
        if(getIntent().getIntExtra("backStatus",0) == 1){
            mBack.setVisibility(View.INVISIBLE);
        }
        mUsername = (EditTextWithDelete) findViewById(R.id.et_username);
        mTvPassword = (EditTextWithDelete) findViewById(R.id.et_password);
        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mTvLoad = (TextView) findViewById(R.id.tv_seller_load);
        mTvForgetPassword = (TextView) findViewById(R.id.tv_fgt_psw);
        rl_seller_jizhu = (RelativeLayout) findViewById(R.id.rl_seller_jizhu);
        iv_seller_password1 = (ImageView) findViewById(R.id.iv_seller_password1);
        iv_seller_password2 = (ImageView) findViewById(R.id.iv_seller_password2);
        tv_seller_jizhumima = (TextView) findViewById(R.id.tv_seller_jizhumima);
        mTvForgetPassword.setText(mTranslatesString.getCommon_forgetpassword());
        UIHelper.setClickable(mBtnLogin, mUsername, mTvPassword);
        mTvPassword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                UIHelper.setClickable(mBtnLogin, mUsername, mTvPassword);
            }
        });
        initText();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initText() {
        mBtnLogin.setText(mTranslatesString.getCommon_login());
        mUsername.setHint(mTranslatesString.getNotice_inputusername());
        mTvPassword.setHint(mTranslatesString.getValidate_inputpassword());
        mTvLoad.setText(mTranslatesString.getCommon_sellerregister());
        mTvForgetPassword.setText(mTranslatesString.getCommon_forgetpassword());
        tv_seller_jizhumima.setText(mTranslatesString.getCommon_rememberpassword());
        //判断本地是否有存的密码，如果有就显示出来，没有就算了
        jieguo = SPHelper.getString("selleruser&password");
        if (!TextUtils.isEmpty(jieguo)){
            //记住了密码
            String[] split = jieguo.split("=");
            mUsername.setText(split[0]);
            mTvPassword.setText("z1c2x3");
        }
    }

    @OnClick({R.id.btn_login, R.id.tv_fgt_psw, R.id.tv_seller_load,R.id.rl_seller_jizhu})
    public void getOnclick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_fgt_psw:
                Intent intent = new Intent(this, ForgetPswActivity.class);
                intent.putExtra(Configs.USER_TYPE.TYPE,Configs.USER_TYPE.SELLER);
                startActivity(intent);
                break;

            case R.id.tv_seller_load:
                Intent intent2 = new Intent(this, RegisterActivity.class);
                intent2.putExtra(Configs.USER_TYPE.TYPE, Configs.USER_TYPE.SELLER);
                startActivity(intent2);
                break;
            case R.id.rl_seller_jizhu:
                if (isPassword){
                    iv_seller_password1.setVisibility(View.GONE);
                    iv_seller_password2.setVisibility(View.VISIBLE);
                }else{
                    iv_seller_password1.setVisibility(View.VISIBLE);
                    iv_seller_password2.setVisibility(View.GONE);
                }
                isPassword=!isPassword;
                break;
            default:
                break;
        }
    }

    private void login() {
        /**
         * 登录之前拼接好时间戳
         */
        SPHelper.putString(Configs.APPNAME, Configs.DOMAIN + "-" + DateUtils.getTimeStamp());
        showProgressDialog(Waiting);
        if (TextUtils.isEmpty(jieguo)||!mTvPassword.getText().toString().equals("z1c2x3")){
            //如果本地没有存密码，或者密码有改变
            rdm.Login(mUsername.getText().toString(), Md5Encrypt.md5(mTvPassword.getText().toString()), unLogin, -1, "",1);
        }else{
            rdm.Login(mUsername.getText().toString(),jieguo.split("=")[1], unLogin, -1, "",1);
        }


        rdm.loginListener = new LoginListener() {
            @Override
            public void onFinish() {
                dismissProgressDialog();
            }

            @Override
            public void onSuccess(int userId, String token, String type, int step,int nextStep) {
                if ("1".equals(type)) {
                    /**
                     * 只要卖家商家信息没有填写完就拦截 让商家从头开始填写
                     * 进入公司商家信息设置页面
                     */
                    if (step != BaseSetCompanyActivity.SELLERSTEPTHIRD) {
                        Intent intent = new Intent(SellerLoginActivity.this, SelectSellerTypeActivity.class);
                        intent.putExtra("type", BaseSetCompanyActivity.LOGIN);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SellerLoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if (isPassword) {
                        SPHelper.putString("selleruser&password", mUsername.getText().toString() + "=" + Md5Encrypt.md5(mTvPassword.getText().toString()));
                    }else{
                        SPHelper.putString("selleruser&password","");
                    }
                } else {
                    ToastHelper.makeErrorToast(mTranslatesString.getCommon_pleaseinputuserinfo());
                }
            }

            @Override
            public void onError(int errorNO, String errorInfo) {
                ToastHelper.makeErrorToast(errorInfo);
            }

            @Override
            public void onLose() {
                ToastHelper.makeErrorToast(request_failure);
            }
        };

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //home键
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
