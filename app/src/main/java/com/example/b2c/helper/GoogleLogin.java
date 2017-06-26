package com.example.b2c.helper;
import android.content.Intent;
 import android.support.v4.app.FragmentActivity;
 import android.util.Log;
 import android.widget.Toast;

import com.example.b2c.R;
import com.google.android.gms.auth.api.Auth;
 import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
 import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
 import com.google.android.gms.auth.api.signin.GoogleSignInResult;
 import com.google.android.gms.common.api.GoogleApiClient;
 import com.google.android.gms.common.api.ResultCallback;
 import com.google.android.gms.common.api.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ThinkPad on 2017/4/10.
 */

public class GoogleLogin {
    public int requestCode = 10 ;
    private FragmentActivity activity ;
    public GoogleSignInOptions gso ;
    public GoogleApiClient mGoogleApiClient ;
    public GoogleApiClient.OnConnectionFailedListener listener ;
    private GoogleSignListener googleSignListener ;

    public GoogleLogin(FragmentActivity activity , GoogleApiClient.OnConnectionFailedListener listener ){
        this.activity = activity ;
        this.listener = listener ;

//初始化谷歌登录服务
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestId()
                .requestIdToken(activity.getString(R.string.server_client_id))
                .requestProfile()
                .build();

// Build a GoogleApiClient with access to GoogleSignIn.API and the options above.
        mGoogleApiClient = new GoogleApiClient.Builder( activity )
                .enableAutoManage( activity , listener )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    /**
     * 登录
     */
    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, requestCode);
    }

    /**
     * 退出登录
     */
    public void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        if ( status.isSuccess() ){
                            if ( googleSignListener != null ){
                                googleSignListener.googleLogoutSuccess();
                            }
                        }else {
                            if ( googleSignListener!= null ){
                                googleSignListener.googleLogoutFail();
                            }
                        }
                    }
                });
    }

    public Map<String,String> handleSignInResult(GoogleSignInResult result) {
        Map<String,String>map=new HashMap<>();

        if (result.isSuccess()) {
//登录成功
            GoogleSignInAccount acct = result.getSignInAccount();
            map.put("thirdId",acct.getId());
            map.put("thirdSource",20+"");
            map.put("email",acct.getEmail());
            map.put("firstName",acct.getGivenName());
            map.put("lastName",acct.getFamilyName());
            map.put("sex","");
            map.put("birthday","");
            map.put("accessToken",acct.getIdToken());
//            res = "登录成功"
//                    + "用户名为：" + acct.getDisplayName()
//                    + "  邮箱为：" + acct.getEmail()
//                    + " token为：" + acct.getIdToken()
//                    + " 头像地址为：" + acct.getPhotoUrl()
//                    + " Id为：" + acct.getId()
//                    + " GrantedScopes为：" + acct.getGrantedScopes() ;
//            Toast.makeText( activity, res, Toast.LENGTH_SHORT).show();
            if ( googleSignListener != null ){
                googleSignListener.googleLoginSuccess();
            }
        } else {
// Signed out, show unauthenticated UI.
            if ( googleSignListener != null ){
                googleSignListener.googleLoginFail();
            }
            map=null;
        }
        return map ;
    }


    public void setGoogleSignListener( GoogleSignListener googleSignListener ){
        this.googleSignListener = googleSignListener ;
    }
public void ss(){
    mGoogleApiClient.stopAutoManage(activity);
    mGoogleApiClient.disconnect();
}
    public interface GoogleSignListener {
        void googleLoginSuccess();
        void googleLoginFail() ;
        void googleLogoutSuccess();
        void googleLogoutFail() ;
    }
}
