package com.example.b2c.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.activity.logistic.LogisticsHomeActivity;
import com.example.b2c.activity.logistic.LogisticsLoginActivity;
import com.example.b2c.activity.seller.BaseSetCompanyActivity;
import com.example.b2c.activity.seller.HomeActivity;
import com.example.b2c.activity.seller.NewSellerHomeActivity;
import com.example.b2c.activity.seller.SelectSellerTypeActivity;
import com.example.b2c.activity.seller.SellerLoginActivity;
import com.example.b2c.activity.seller.Setup2CompanyActivity;
import com.example.b2c.activity.seller.Setup3CompanyActivity;
import com.example.b2c.activity.staff.StaffHomeActivity;
import com.example.b2c.adapter.widget.SpinerAdapter;
import com.example.b2c.config.Configs;
import com.example.b2c.helper.GoogleLogin;
import com.example.b2c.helper.NotifyHelper;
import com.example.b2c.helper.SPHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.helper.UIHelper;
import com.example.b2c.net.listener.LoginListener;
import com.example.b2c.net.listener.base.NoDataListener;
import com.example.b2c.net.response.ResponseResult;
import com.example.b2c.net.util.DateUtils;
import com.example.b2c.net.util.Md5Encrypt;
import com.example.b2c.net.zthHttp.OkhttpUtils;
import com.example.b2c.observer.module.HomeObserver;
import com.example.b2c.widget.EditTextWithDelete;
import com.example.b2c.widget.SimpleTextWatcher;
import com.example.b2c.widget.SpinerPopWindow;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 买家登录页面
 */
public class BuyerLoginActivity extends TempBaseActivity implements OnClickListener,SpinerAdapter.IOnItemSelectListener,
        GoogleApiClient.OnConnectionFailedListener , GoogleLogin.GoogleSignListener{

    private Button btn_login;
    private TextView tv_register, tv_forget_psw;
    private EditTextWithDelete et_username, et_password;
    private Bitmap[] LocalBitmaps;

    private String username, password;
    //2.0
    private ImageView face_book_login;
    private ImageView google_login;
    private CallbackManager callbackManager;
    private OkhttpUtils instance;
//    private FeacbookLoginBean feacbookLoginBean;
    private String id;
    private String firstName;
    private String lastName;
    private String gender;
    private String emali;
    private String date;
    private String token;
    private ResponseResult result;
    private ImageView iv_select_password1;
    private ImageView iv_select_password2;
    private TextView tv_jizhumima;
    //定义一个状态用来记录是否记住了密码
    private boolean isPassword=true;
    private String jieguo;
    private RelativeLayout rl_jizhu;


    private  TextView tv_value;
    private  TextView tv_label;
    private SpinerAdapter mAdapter;
    private List<String> mListType = new ArrayList<String>();  //类型列表
    private LinearLayout bt_dropdown;
    //设置PopWindow
    private SpinerPopWindow mSpinerPopWindow;
    private GoogleLogin googleLogin;
    //用来记录用户选择的角色
    private int userType;
    private boolean showGoogle;
    @Override
    public int getRootId() {
        return R.layout.login_layout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        facebookLgin();
        instance = OkhttpUtils.getInstance();
        initView();
    }
    public void initView() {
        face_book_login = (ImageView) findViewById(R.id.face_book_login);
        google_login = (ImageView) findViewById(R.id.google_login);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_register = (TextView) findViewById(R.id.tv_register);
        et_username = (EditTextWithDelete) findViewById(R.id.et_username);
        et_password = (EditTextWithDelete) findViewById(R.id.et_password);
        tv_forget_psw = (TextView) findViewById(R.id.tv_fgt_psw);
        rl_jizhu = (RelativeLayout) findViewById(R.id.rl_jizhu);
        iv_select_password1 = (ImageView) findViewById(R.id.iv_select_password1);
        iv_select_password2 = (ImageView) findViewById(R.id.iv_select_password2);
        tv_jizhumima = (TextView) findViewById(R.id.tv_jizhumima);
        tv_value = (TextView) findViewById(R.id.tv_value);
        tv_label = (TextView) findViewById(R.id.tv_label);
        bt_dropdown = (LinearLayout) findViewById(R.id.bt_dropdown);
        LocalBitmaps = new Bitmap[]{BitmapFactory.decodeResource(getResources(), R.drawable.eye_open), BitmapFactory.decodeResource(getResources(), R.drawable.eye_close)};

        btn_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_forget_psw.setOnClickListener(this);
        face_book_login.setOnClickListener(this);
        google_login.setOnClickListener(this);
        rl_jizhu.setOnClickListener(this);

        bt_dropdown.setOnClickListener(this);

        //初始化类型集合
        mListType.add(mTranslatesString.getCommon_buyer());
        mListType.add(mTranslatesString.getCommon_seller());
        mListType.add(mTranslatesString.getCcommon_wuliu());
        mAdapter = new SpinerAdapter(this,mListType);
        mAdapter.refreshData(mListType,0);

        //显示第一条数据
        //mTView.setText(names[0]);

        //初始化PopWindow
        mSpinerPopWindow = new SpinerPopWindow(this);
        mSpinerPopWindow.setAdatper(mAdapter);
        mSpinerPopWindow.setItemListener(this);
        initText();
    }

    public void initText() {
//        setTitle(mTranslatesString.getCommon_login());
        setTitle(mTranslatesString.getCommon_buyerlogin());
        et_username.setHint(mTranslatesString.getNotice_inputusername());
        et_password.setHint(mTranslatesString.getValidate_inputpassword());
        btn_login.setText(mTranslatesString.getCommon_login());
        tv_register.setText(mTranslatesString.getCommon_register());
        tv_forget_psw.setText(mTranslatesString.getCommon_forgetpassword());
        tv_jizhumima.setText(mTranslatesString.getCommon_rememberpassword());
        tv_label.setText(mTranslatesString.getCommon_selectjuese());
        tv_value.setText(mTranslatesString.getCommon_buyer());
        UIHelper.setClickable(btn_login, et_username, et_password);
        et_password.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                UIHelper.setClickable(btn_login, et_username, et_password);
            }
        });
        //判断本地是否有存的密码，如果有就显示出来，没有就算了
        jieguo = SPHelper.getString("buyeruser&password");
        String jizhuType = SPHelper.getString("jizhuType");
        if (!TextUtils.isEmpty(jieguo)){
            //记住了密码
            String[] split = jieguo.split("=");
            et_username.setText(split[0]);
            et_password.setText("z1c2x3");
            if (jizhuType.equals("0")){
                userType=0;
                tv_value.setText(mListType.get(0));
                setTitle(mTranslatesString.getCommon_buyerlogin());
            }
            if (jizhuType.equals("1")){
                userType=1;
                tv_value.setText(mListType.get(1));
                face_book_login.setVisibility(View.GONE);
                google_login.setVisibility(View.GONE);
                setTitle(mTranslatesString.getCommon_shopLogin());
            }
            if (jizhuType.equals("2")||jizhuType.equals("3")){
                userType=2;
                tv_value.setText(mListType.get(2));
                face_book_login.setVisibility(View.GONE);
                google_login.setVisibility(View.GONE);
                tv_register.setVisibility(View.GONE);
                setTitle(mTranslatesString.getLogistics_logintitle());
            }
        }

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Login();
                break;
            case R.id.tv_register:
                Intent i_register = new Intent(this, RegisterActivity.class);
                i_register.putExtra(Configs.USER_TYPE.TYPE, Configs.USER_TYPE.BUYER);
                startActivity(i_register);
                break;
            case R.id.tv_fgt_psw:
                Intent i_fgt_psw = new Intent(this, ForgetPswActivity.class);
                i_fgt_psw.putExtra(Configs.USER_TYPE.TYPE,Configs.USER_TYPE.BUYER);
                startActivity(i_fgt_psw);
                break;
            case R.id.face_book_login://facebook登录
                LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile","email","user_friends"));
                break;
            case R.id.google_login://谷歌登录
                showLoading();
                if (!showGoogle){
                    googleLogin = new GoogleLogin(BuyerLoginActivity.this ,this );
                    googleLogin.setGoogleSignListener(this);
                    showGoogle=true;
                }
                //登录
                 googleLogin.signIn();
                break;
            case R.id.rl_jizhu:
                if (isPassword){
                    iv_select_password1.setVisibility(View.GONE);
                    iv_select_password2.setVisibility(View.VISIBLE);
                }else{
                    iv_select_password1.setVisibility(View.VISIBLE);
                    iv_select_password2.setVisibility(View.GONE);
                }
                isPassword=!isPassword;
                break;
            case R.id.bt_dropdown:
                mSpinerPopWindow.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
                mSpinerPopWindow.showAsDropDown(tv_value);

                break;
            default:
                break;
        }
    }

    public void Login() {
        /**
         * 登录之前拼接好时间戳
         */
        SPHelper.putString(Configs.APPNAME, Configs.DOMAIN + "-" + DateUtils.getTimeStamp());
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (username.equals("") || password.equals("")) {
            ToastHelper.makeToast(mTranslatesString.getCommon_inputnotnull());
            return;
        }
        showProgressDialog(Waiting);
        //如果本地存的有就不用加密了，没有就加密
        if (TextUtils.isEmpty(jieguo)||!password.equals("z1c2x3")){
            //如果本地没有存密码，或者密码有改变
            rdm.Login(username, Md5Encrypt.md5(password), unLogin, userId, token,userType);
        }else{
            String s = jieguo.split("=")[1];
            rdm.Login(username,s , unLogin, userId, token,userType);
        }
        rdm.loginListener = new LoginListener() {
            @Override
            public void onSuccess(int userId, String token, String nickName, int nextStep,int companyType) {
                //登录成功
                //登录成功将密码存到本地
                if (isPassword) {
                    if  ((TextUtils.isEmpty(jieguo) ||!password.equals("z1c2x3"))){
                       //本地存的有密码，并且没有修改密码
                        SPHelper.putString("buyeruser&password", username + "=" + Md5Encrypt.md5(password));
                    }else{
                        SPHelper.putString("buyeruser&password", username + "=" + jieguo.split("=")[1]);
                    }
                    SPHelper.putString("jizhuType", nickName);
                }else{
                    SPHelper.putString("buyeruser&password","");
                    SPHelper.putString("jizhuType","");
                }

                if (nickName.equals("0")) {
                    //如果是买家
                    startActivity(new Intent(BuyerLoginActivity.this,MainActivity.class));
                    NotifyHelper.setNotifyData("home", new HomeObserver(0, ""));
                }if (nickName.equals("1")){
                        //卖家
                    /**
                     * 只要卖家商家信息没有填写完就拦截 让商家从头开始填写
                     * 进入公司商家信息设置页面
                     */
                    if (nextStep ==4) {//nextStep是4的时候说明卖家的资料已经填写完了
                        Intent intent = new Intent(BuyerLoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }  if (nextStep==3){
                            //跳转到注册的第三步
                        Intent intent = new Intent(BuyerLoginActivity.this, Setup3CompanyActivity.class);
                        intent.putExtra("type",BaseSetCompanyActivity.LOGIN);
                        intent.putExtra("sellerType",companyType);
                        startActivity(intent);

                    }if (nextStep==2){
                        //跳转到注册的第二步
                        Intent intent = new Intent(BuyerLoginActivity.this, Setup2CompanyActivity.class);
                        intent.putExtra("type",BaseSetCompanyActivity.LOGIN);
                        intent.putExtra("sellerType",companyType);
                        startActivity(intent);
                    }
                    if (nextStep==1){
                        //根据nextStep和companyType判断往哪跳转
                        //nextStep来判断资料填写到哪了，companyType来判断是个人还是商家
                        Intent intent = new Intent(BuyerLoginActivity.this, SelectSellerTypeActivity.class);
                        intent.putExtra("type", BaseSetCompanyActivity.LOGIN);
                        startActivity(intent);
                    }
                }
                if (nickName.equals("2")){
                    //快递公司
                    getIntent(BuyerLoginActivity.this, LogisticsHomeActivity.class);
                }
                if (nickName.equals("3")){
                    //快递员
                    getIntent(BuyerLoginActivity.this, StaffHomeActivity.class);
                }
                finish();
            }

            @Override
            public void onError(int errorNO, String errorInfo) {
                ToastHelper.makeErrorToast(errorInfo);
            }
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
    private void facebookLgin(){
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                showLoading();
                updateUI(loginResult.getAccessToken());
                //成功后调用接口，将这个id传到后台，如果有就直接登录，没有跳转到发送验证码的页面
            }
            @SuppressWarnings("deprecation")
            @Override
            public void onCancel() {
                //取消授权
                CookieSyncManager.createInstance(BuyerLoginActivity.this);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                CookieSyncManager.getInstance().sync();
            }//{HttpStatus: -1, errorCode: 100, errorType: null, errorMessage: Invalid access_token}
            @Override
            public void onError(FacebookException e) {
                if (e instanceof FacebookAuthorizationException){
                    if (AccessToken.getCurrentAccessToken() != null) {
                        LoginManager.getInstance().logOut();
                    }
                }
                e.getStackTrace();
            }
        });
    }


    /**
     * facebopok获取用户信息
     * @param accessToken
     */
    private void updateUI(final AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                         @Override
                         public void onCompleted(JSONObject object, GraphResponse response) {
                                 if (object != null) {
                                     //比如:1565455221565
                                     id = object.optString( "id" );
                                     //比如：Zhang San
                                     firstName = object.optString( "first_name" );
                                     //比如：Zhang San
                                     lastName = object.optString( "last_name" );
                                     //性别：比如 male （男）  female （女）
                                     gender = object.optString("gender");
                                     //邮箱：比如：56236545@qq.com
                                     emali = object.optString("email");
                                     //生日
                                     date = object.optString("birthday");
                                     //zh_CN 代表中文简体
                                     token = accessToken.getToken();
                                        //判断是否有邮箱
                                         mLogisticsDataConnection.requestLogin(getApplication(),id, emali, firstName, lastName, gender, date,accessToken.getToken());
                                     mLogisticsDataConnection.mNodataListener=new NoDataListener() {
                                         @Override
                                         public void onSuccess(String success) {
                                             startActivity(new Intent(getApplication(),MainActivity.class));
                                             finish();
                                             NotifyHelper.setNotifyData("home", new HomeObserver(0, ""));
                                         }

                                         @Override
                                         public void onError(int errorNo, String errorInfo) {
                                             if (errorNo==3){
                                                 Intent intent= new Intent(BuyerLoginActivity.this,GetEmaliNumber.class);
                                                 intent.putExtra("id",id);
                                                 intent.putExtra("firstName",firstName);
                                                 intent.putExtra("lastName",lastName);
                                                 intent.putExtra("gender",gender);
                                                 intent.putExtra("date",date);
                                                 intent.putExtra("token",token);
                                                 startActivity(intent);
                                                 dissLoading();
                                             }
                                             Toast.makeText(getApplication(),errorInfo,Toast.LENGTH_LONG).show();
                                         }

                                         @Override
                                         public void onFinish() {
                                            dissLoading();
                                         }

                                         @Override
                                         public void onLose() {
                                                    dissLoading();
                                         }
                                     };
                                 }
                             }
                     }) ;
                 Bundle parameters = new Bundle();
                 parameters.putString("fields", "id,name,link,gender,birthday,email,picture,locale,updated_time,timezone,age_range,first_name,last_name");
                 request.setParameters(parameters);
                 request.executeAsync() ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //facebook的回调
        callbackManager.onActivityResult(requestCode, resultCode, data);
        //谷歌登录成功回调
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        //谷歌登录成功回调
        if (requestCode == 10 ) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            Map<String, String> googleMap = googleLogin.handleSignInResult(result);
            //调用接口
            mLogisticsDataConnection.googleLogin(googleMap,getApplication());
            mLogisticsDataConnection.mNodataListener=new NoDataListener() {
                @Override
                public void onSuccess(String success) {
                    startActivity(new Intent(getApplication(),MainActivity.class));
                    finish();
                    NotifyHelper.setNotifyData("home", new HomeObserver(0, ""));
                    dissLoading();
                }

                @Override
                public void onError(int errorNo, String errorInfo) {
                    ToastHelper.makeToast(errorInfo);
                }

                @Override
                public void onFinish() {
                    dissLoading();
                }

                @Override
                public void onLose() {
                    dissLoading();
                }
            };
        }
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
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

    /* (non-Javadoc)
     * @see org.gaochun.adapter.SpinerAdapter.IOnItemSelectListener#onItemClick(int)
     */
    @Override
    public void onItemClick(int pos) {
        userType=pos;
        // TODO Auto-generated method stub
        if (pos >= 0 && pos <= mListType.size()){
            String value = mListType.get(pos);
            tv_value.setText(value);
            if (pos!=0){
                //如果不是买家，隐藏谷歌，facebook登录按钮
                face_book_login.setVisibility(View.GONE);
                google_login.setVisibility(View.GONE);
                if(pos==2){
                    //如果是物流连注册按钮都隐藏
                    tv_register.setVisibility(View.GONE);
                    setTitle(mTranslatesString.getLogistics_logintitle());
                }else{
                    //如果是卖家
                    setTitle(mTranslatesString.getCommon_shopLogin());
                    tv_register.setVisibility(View.VISIBLE);
                }
            }else{
                setTitle(mTranslatesString.getCommon_buyerlogin());
                face_book_login.setVisibility(View.VISIBLE);
                google_login.setVisibility(View.VISIBLE);
                tv_register.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        dissLoading();
        ToastHelper.makeToast(mTranslatesString.getCommon_loginShibai());
    }

    @Override
    public void googleLoginSuccess() {
    }

    @Override
    public void googleLoginFail() {
        dissLoading();
    }

    @Override
    public void googleLogoutSuccess() {
        dissLoading();
    }

    @Override
    public void googleLogoutFail() {
        dissLoading();
    }
}
