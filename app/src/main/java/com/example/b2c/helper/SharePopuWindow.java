package com.example.b2c.helper;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.net.response.translate.MobileStaticTextCode;

/**
 * Created by ThinkPad on 2017/4/17.
 */

public abstract class SharePopuWindow {
    private Activity context;
    private PopupWindow popupWindow;
    private LinearLayout mRight;
    private TextView tv_go_home;
    private LinearLayout ll_gohome;
    private TextView tv_go_message;
    private LinearLayout ll_go_message;
    private TextView tv_fenxiang;
    private LinearLayout ll_facebook_share;
private MobileStaticTextCode mTranslatesString;
    public SharePopuWindow(Activity context, LinearLayout mRight,MobileStaticTextCode mTranslatesString) {
        this.context = context;
        this.mRight = mRight;
        this.mTranslatesString=mTranslatesString;
    }

    public void showPopwindow() {
        // 获取自定义布局文件activity_popupwindow_left.xml的视图
        View popupWindow_view = context.getLayoutInflater().inflate(R.layout.layout_subject, null, false);
        // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度

        popupWindow = new PopupWindow(popupWindow_view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        ll_gohome= (LinearLayout) popupWindow_view.findViewById(R.id.ll_gohome);
        ll_go_message= (LinearLayout) popupWindow_view.findViewById(R.id.ll_go_message);
        ll_facebook_share= (LinearLayout) popupWindow_view.findViewById(R.id.ll_facebook_share);
        tv_go_home= (TextView) popupWindow_view.findViewById(R.id.tv_go_home);
        tv_go_message= (TextView) popupWindow_view.findViewById(R.id.tv_go_message);
        tv_fenxiang= (TextView) popupWindow_view.findViewById(R.id.tv_fenxiang);
        tv_go_home.setText(mTranslatesString.getCommon_home());
        tv_go_message.setText(mTranslatesString.getCommon_message());
        tv_fenxiang.setText(mTranslatesString.getCommon_share()+" Facebook");
        ll_gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goHome();
            }
        });
        ll_go_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMessage();
            }
        });
        ll_facebook_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goShare();
            }
        });
        // 设置动画效果
        // 点击其他地方消失
        popupWindow_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                return false;
            }
        });
        popupWindow.showAsDropDown(mRight, 0, 0);
    }

    /**
     * 设置添加屏幕的背景透明度 * * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        context.getWindow().setAttributes(lp);
    }
    public abstract void goHome();
    public abstract void goMessage();
    public abstract void goShare();
}
