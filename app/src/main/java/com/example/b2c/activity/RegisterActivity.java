package com.example.b2c.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.activity.seller.BaseSetCompanyActivity;
import com.example.b2c.activity.seller.SelectSellerTypeActivity;
import com.example.b2c.activity.seller.SellerLoginActivity;
import com.example.b2c.adapter.widget.SpinerAdapter;
import com.example.b2c.config.Configs;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.NotifyHelper;
import com.example.b2c.helper.SPHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.exception.NetException;
import com.example.b2c.net.listener.RegisterListener;
import com.example.b2c.net.listener.ResponseListener;
import com.example.b2c.net.listener.ReturnFromServerListener;
import com.example.b2c.net.listener.base.NoDataListener;
import com.example.b2c.net.response.Response;
import com.example.b2c.net.response.ResponseResult;
import com.example.b2c.net.response.Users;
import com.example.b2c.net.util.BaseValidator;
import com.example.b2c.net.util.DateUtils;
import com.example.b2c.net.util.HttpClientUtil;
import com.example.b2c.observer.module.HomeObserver;
import com.example.b2c.widget.EditTextWithDelete;
import com.example.b2c.widget.SpinerPopWindow;
import com.google.gson.Gson;

import org.apache.http.HttpStatus;
import org.apache.http.util.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 买家、卖家注册
 */
public class RegisterActivity extends TempBaseActivity implements OnClickListener, SpinerAdapter.IOnItemSelectListener {

    /**
     * 注册来源
     */
    public static final int SOURCE = 20;

    /**
     * 获取验证码类型标示符、、买家
     */
    public static final int GETEMAILCODE_BUYER = 1;
    /**
     * 获取验证码类型标示符、、卖家
     */
    public static final int GETEMAILCODE_SELLER = 5;

    /**
     * 注册买家还是注册卖家
     */
//    private int type;

    private Button btn_register_ok;
    private Button btn_register_code;
    private EditTextWithDelete et_register_username, et_register_email, et_register_code,
            et_register_phone, et_register_password, et_register_repassword;

    private String loginName, phone;//登录名，电话
    private TextView protocol_label;
    private TextView protocol_link;

    private CountDownTimer timer;
    private final long countDownTime = 60 * 1000;
    private RadioButton phone_zhuce;
    private RadioButton email_zhuce;
    private TextView tv_zhuce;
    private TextView tv_zhuce_maijia;
    private RelativeLayout ll_juese_select;
    private RadioGroup rg_select;
    private RelativeLayout rl_mun_code;
    private int checkType=2;//记录是手机注册还是邮箱注册
    private int userType;//用户的类型
    private List<String> mListType;
    private SpinerAdapter mAdapter;
    private SpinerPopWindow mSpinerPopWindow;

    @Override
    public int getRootId() {
        return R.layout.activity_seller_register;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListType = new ArrayList<String>();
        initView();
        initText();
        initLinter();
    }

    public void initView() {
//        type = getIntent().getIntExtra(Configs.USER_TYPE.TYPE, Configs.USER_TYPE.BUYER);

        btn_register_ok = (Button) findViewById(R.id.btn_register_ok);
        btn_register_code = (Button) findViewById(R.id.btn_register_code);

        et_register_username = (EditTextWithDelete) findViewById(R.id.et_register_username);
        et_register_email = (EditTextWithDelete) findViewById(R.id.et_register_email);

        et_register_email.setmOnAfterTextChange(new EditTextWithDelete.OnAfterTextChange() {
            @Override
            public void doOnAfterTextChange() {
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                    btn_register_code.setEnabled(true);
                    btn_register_code.setText(mTranslatesString.getCommon_authcode());
                }
            }
        });

        et_register_code = (EditTextWithDelete) findViewById(R.id.et_register_code);
        et_register_phone = (EditTextWithDelete) findViewById(R.id.et_register_phone);
        et_register_password = (EditTextWithDelete) findViewById(R.id.et_register_password);
        et_register_repassword = (EditTextWithDelete) findViewById(R.id.et_register_repassword);

        protocol_label = (TextView) findViewById(R.id.protocol_label);
        protocol_label.setText(mTranslatesString.getCommon_registerisaggree());
        protocol_link = (TextView) findViewById(R.id.protocol_link);
        protocol_link.setText(Html.fromHtml("<u>" + "《" + mTranslatesString.getCommon_userprotocol() + "》" + "</u>"));

        btn_register_ok.setOnClickListener(this);
        btn_register_code.setOnClickListener(this);

        protocol_link.setOnClickListener(this);
        rg_select = (RadioGroup) findViewById(R.id.rg_select);
        phone_zhuce = (RadioButton) findViewById(R.id.rb_phone_zhuce);
        email_zhuce = (RadioButton) findViewById(R.id.rb_email_zhuce);
        tv_zhuce = (TextView) findViewById(R.id.tv_zhuce);
        tv_zhuce_maijia = (TextView) findViewById(R.id.tv_zhuce_maijia);
        ll_juese_select = (RelativeLayout) findViewById(R.id.ll_juese_select);
        rl_mun_code = (RelativeLayout) findViewById(R.id.rl_mun_code);
        ll_juese_select.setOnClickListener(this);
        mListType.add(mTranslatesString.getCommon_buyer());
        mListType.add(mTranslatesString.getCommon_seller());
        mAdapter = new SpinerAdapter(this,mListType);
        mAdapter.refreshData(mListType,0);

        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);

        tv_zhuce_maijia.setText(mTranslatesString.getCommon_buyer());
    }

    public void initText() {
        setTitle(mTranslatesString.getCommon_buyerregister());
        et_register_username.setHint(mTranslatesString.getCommon_username());
        et_register_email.setHint(mTranslatesString.getCommon_email());
        et_register_code.setHint(mTranslatesString.getCommon_authcode());
        et_register_phone.setHint(mTranslatesString.getCommon_tellphone());
        et_register_password.setHint(mTranslatesString.getCommon_password());
        et_register_repassword.setHint(mTranslatesString.getCommon_surepassword());
        btn_register_ok.setText(mTranslatesString.getCommon_sure());
        btn_register_code.setText(mTranslatesString.getCommon_getauthcode());

        phone_zhuce.setText(mTranslatesString.getCommon_phonezhuce());
        email_zhuce.setText(mTranslatesString.getCommon_emailzhuce());
        tv_zhuce.setText(mTranslatesString.getCommon_zzhuce());
    }
    private void initLinter(){
        rg_select.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rb_phone_zhuce:
                        //手机注册要把获取按钮显示出来，还有输入验证码的框也显示出来
                        btn_register_code.setVisibility(View.VISIBLE);
                        rl_mun_code.setVisibility(View.VISIBLE);
                        checkType=1;
                        break;
                    case R.id.rb_email_zhuce:
                        btn_register_code.setVisibility(View.GONE);
                        rl_mun_code.setVisibility(View.GONE);
                        checkType=2;
                        break;
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent;
//        if (type == Configs.USER_TYPE.BUYER) {
//            intent = new Intent(this, BuyerLoginActivity.class);
//        } else {
//            intent = new Intent(this, SellerLoginActivity.class);
//        }
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register_code:
                getEmailVerifyCode();
                break;
            case R.id.btn_register_ok:
                register();
                break;
            case R.id.protocol_link:
                seeProtocol();
                break;
            case R.id.ll_juese_select:
                mSpinerPopWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mSpinerPopWindow.showAsDropDown(tv_zhuce_maijia);
                break;
            default:
                break;
        }
    }

    /**
     * 查看用户协议
     */
    private void seeProtocol() {
        if (userType == Configs.USER_TYPE.BUYER) {
            Intent intent = new Intent(RegisterActivity.this, ProtocolActivity.class);
            intent.putExtra("type", userType);
            startActivity(intent);
        } else if (userType == Configs.USER_TYPE.SELLER) {
            Intent intent = new Intent(RegisterActivity.this, ProtocolActivity.class);
            intent.putExtra("type", userType);
            startActivity(intent);
        }

    }

    public void register() {
        /**
         * 注册之前拼接好时间戳
         */
        SPHelper.putString(Configs.APPNAME, Configs.DOMAIN + "-" + DateUtils.getTimeStamp());

        loginName = et_register_username.getText().toString();
        //email 提到全局了
        String emailCode = et_register_code.getText().toString();
        String mobile = et_register_phone.getText().toString();
        String password = et_register_password.getText().toString();
        String rePassword = et_register_repassword.getText().toString();
        String mEmail =  et_register_email.getText().toString();
        if (TextUtils.isBlank(loginName) || TextUtils.isBlank(mEmail) || TextUtils.isBlank(password)) {
            ToastHelper.makeToast(mTranslatesString.getCommon_inputnotnull());
            return;
        } else if (!password.equals(rePassword)) {
            ToastHelper.makeToast(mTranslatesString.getValidate_passworddifference());
            return;
        }
        showProgressDialog(Waiting);
        Map map = new HashMap();
        map.put("loginName", loginName);
        map.put("email", mEmail);
        if (checkType==1) {
            map.put("mobileCode", emailCode);
        }
        map.put("mobile", mobile);
        map.put("password", password);
        map.put("type", userType);
        map.put("source", SOURCE);
        map.put("checkType", checkType);
        rdm.Register(map);
        rdm.registerListener = new RegisterListener() {
            @Override
            public void onSuccess(int userId, String token, int type) {
                SPHelper.putString("buyeruser&password","");
                SPHelper.putString("jizhuType","");
                if (checkType==2){
                    //要重新登录
                    ToastHelper.makeToast(mTranslatesString.getNotice_registersuccess()+","+mTranslatesString.getCommon_zhucetishi());
                    startActivity(new Intent(RegisterActivity.this,BuyerLoginActivity.class));
                }else {
                    if (type == Configs.USER_TYPE.BUYER) {
                        /**
                         * 买家注册成功，直接进入买家app首页面
                         */
                        ToastHelper.makeToast(mTranslatesString.getNotice_registersuccess());
                        Users user = new Users();
                        user.setLoginName(loginName);
                        user.setToken(token);
                        user.setUserId(userId);
                        user.setUserType(type);
                        SPHelper.putInt(Configs.USER_TYPE.TYPE, type);
                        SPHelper.putBean(Configs.BUYER.INFO, user);
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        NotifyHelper.setNotifyData("home", new HomeObserver(0, ""));
                    }
                    if (type == Configs.USER_TYPE.SELLER) {

                        /**
                         * 卖家注册成功,需要判断填写公司信息
                         */
                        ToastHelper.makeToast(mTranslatesString.getCommon_zhucechengong());
                        Users user = new Users();
                        user.setLoginName(loginName);
                        user.setToken(token);
                        user.setUserId(userId);
                        user.setUserType(type);
                        SPHelper.putInt(Configs.USER_TYPE.TYPE, type);
                        SPHelper.putBean(Configs.SELLER.INFO, user);
                        /**
                         * 进入公司商家信息设置页面
                         */
                        Intent intent = new Intent(RegisterActivity.this, SelectSellerTypeActivity.class);
                        intent.putExtra("type", BaseSetCompanyActivity.REGISTER);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onError(int errorNO, String errorInfo) {
                ToastHelper.makeErrorToast(errorInfo);}
            @Override
            public void onLose() {
                ToastHelper.makeErrorToast(request_failure);
            }
            @Override
            public void onFinish() {
                dismissProgressDialog();
            }
        };
    }

    public void getEmailVerifyCode() {
        phone = et_register_phone.getText().toString();
        if (TextUtils.isBlank(phone)) {
            ToastHelper.makeToast(mTranslatesString.getCommon_pleaseinputemail());
            return;
        }
        if (!BaseValidator.MobileMatch(phone)) {
            ToastHelper.makeToast(mTranslatesString.getCommon_mobilestyleerror());
            return;
        }
        btn_register_code.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getPhoneCode(ConstantS.BASE_URL+"user/sendMobileCode.htm",phone);
            }
        }).start();
    }
    private void getVcodeSuccess() {
        btn_register_code.setText(countDownTime / 1000 + "");
        timer = new CountDownTimer(countDownTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_register_code.setText(millisUntilFinished / 1000 + mTranslatesString.getCommon_smstitle());
            }
            @Override
            public void onFinish() {
                btn_register_code.setEnabled(true);
                btn_register_code.setText(mTranslatesString.getCommon_authcode());
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    //角色选择的条目点击
    @Override
    public void onItemClick(int pos) {
        if (pos >= 0 && pos <= mListType.size()){
            userType=pos;
            String value = mListType.get(pos);
            tv_zhuce_maijia.setText(value);
            if (pos==0){
                setTitle(mTranslatesString.getCommon_buyerregister());
            }else{
                setTitle(mTranslatesString.getCommon_sellerregister());
            }
        }
    }
    /**
     * 第四期获取手机号的接口
     */
    public void getPhoneCode(String url,String mobile){
        Gson gson = new Gson();
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        try {
            Response response = HttpClientUtil.doPost(url, map, false, -1, "");
            if (response.getStatusCode() == HttpStatus.SC_OK) {
                String strResult = response.getContent();
                JSONObject meta_json = new JSONObject(strResult)
                        .getJSONObject("meta");
                ResponseResult result = gson.fromJson(meta_json.toString(),
                        ResponseResult.class);
                if (result.isSuccess()) {
                    ToastHelper.makeToast(result.getErrorInfo());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            getVcodeSuccess();
                        }
                    });
                } else {
                    ToastHelper.makeToast(result.getErrorInfo());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btn_register_code.setEnabled(true);
                        }
                    });
                }
            } else {
                ToastHelper.makeToast(request_failure);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btn_register_code.setEnabled(true);
                    }
                });
            }

        } catch (NetException e) {

        } catch (JSONException e) {
            Log.e("doGetMobileCode", e.getMessage());
        } finally {
           ; dismissProgressDialog();
        }
    }
}
