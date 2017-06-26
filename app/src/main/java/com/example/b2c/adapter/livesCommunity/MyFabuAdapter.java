package com.example.b2c.adapter.livesCommunity;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.net.response.livesCommunity.HomeList;

import com.example.b2c.net.response.translate.MobileStaticTextCode;

import java.util.List;

/**
 * Created by ThinkPad on 2017/3/24.
 */

public abstract class MyFabuAdapter extends XRcycleViewAdapterBase{
    private ImageView iv_lives_list;
    private TextView tv_list_title;
    private TextView tv_lives_time;
    private LinearLayout ll_my_lives;
    private TextView my_lives_delete;
    private LinearLayout swipeMenuLayout;

    public MyFabuAdapter() {
    }

    private Activity context;
    private List<HomeList.Rows> rows;
    private MobileStaticTextCode mTranslatesString;
    public MyFabuAdapter(Activity context, List<HomeList.Rows> rows,MobileStaticTextCode mTranslatesString) {
        this.context = context;
        this.rows = rows;
        this.mTranslatesString=mTranslatesString;
    }

    @Override
    public int getItemPostion() {
        return rows.size();
    }

    @Override
    public int getLayoutID() {
        return R.layout.item_lives_communit_fabu;
    }

    @Override
    public Context getContext() {
        return context;
    }
    @Override
    public void initData(XRcycleViewAdapterBase.MyViewHolder holder, final int position) {
        if (!TextUtils.isEmpty(rows.get(position).getDefaultPic())){
            ImageHelper.display(ConstantS.BASE_PIC_URL+rows.get(position).getDefaultPic(),iv_lives_list);
        }
        tv_list_title.setText(rows.get(position).getTitle());
        tv_lives_time.setText(rows.get(position).getCreateTime());
        swipeMenuLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caozuo(0,position);
            }
        });
        my_lives_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                caozuo(1,position);
            }
        });
    }
    @Override
    public void initView(View itemView) {
        iv_lives_list= (ImageView) itemView.findViewById(R.id.iv_lives_list);
        tv_list_title= (TextView) itemView.findViewById(R.id.tv_list_title);
        tv_lives_time =(TextView) itemView.findViewById(R.id.tv_lives_time);
        swipeMenuLayout = (LinearLayout) itemView.findViewById(R.id.item_view);
        my_lives_delete =(TextView) itemView.findViewById(R.id.my_lives_delete);
        my_lives_delete.setText(mTranslatesString.getCommon_delete());

    }
    public abstract void caozuo(int type,int positons);

}
