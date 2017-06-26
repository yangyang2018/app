package com.example.b2c.adapter.seller;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.net.response.seller.OrderDetail;
import com.example.b2c.net.response.seller.OrderList;
import com.example.b2c.net.response.seller.TuiHuoBean;
import com.example.b2c.net.response.seller.TuiHuoOrder;
import com.example.b2c.net.response.translate.MobileStaticTextCode;
import com.example.b2c.widget.nestlistview.NestFullListView;
import com.example.b2c.widget.nestlistview.NestFullListViewAdapter;
import com.example.b2c.widget.nestlistview.NestFullViewHolder;

import java.util.List;

/**
 * Created by ThinkPad on 2017/4/21.
 */

public abstract class MySealesReturnAdapter extends XRcycleViewAdapterBase {
    private Context context;
    private List<TuiHuoBean> mOrderLists;
    private MobileStaticTextCode mTranslatesString;
    private TextView tv_new_order_title;
    private TextView tv_new_order_num;
    private TextView tv_new_order_quxiao;
    private TextView tv_new_order_queding;
    private TextView xidanshijian;
    private TextView tv_xiadan_time;
    private LinearLayout rl_order_dianji;
    private LinearLayout ll_order_buyes_message;
    private LinearLayout ll_order_time;
    private TextView tv_order_buyersname;
    private TextView tv_order_contact;
    private TextView quanbu_money;
    private TextView tv_order_money;
private int type;
    private ImageView iv_new_order;
    private TextView tv_new_order_biaoti;
    private TextView tv_new_order_content;
    private TextView tv_new_order_jiage;
    private TextView tv_new_order_shuliang;

    public MySealesReturnAdapter(Context context, List<TuiHuoBean> mOrderLists,
                                 MobileStaticTextCode mTranslatesString, int type) {
        this.context = context;
        this.mOrderLists = mOrderLists;
        this.mTranslatesString=mTranslatesString;
        this.type=type;
    }

    @Override
    public int getItemPostion() {
        return mOrderLists.size();
    }

    @Override
    public int getLayoutID() {
        return R.layout.seales_return_adapter;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void initData(MyViewHolder holder, final int position) {
        tv_new_order_title.setText(mTranslatesString.getOrder_no()+":");
        xidanshijian.setText(mTranslatesString.getSeller_ordertime()+": ");
        tv_new_order_queding.setText(mTranslatesString.getCommon_makesure());
        tv_new_order_quxiao.setText(mTranslatesString.getNotice_cancel());
        quanbu_money.setText(mTranslatesString.getCommon_totalprice()+": ");
        tv_order_money.setText("Ks "+mOrderLists.get(position).getTotalPrice());
        tv_new_order_num.setText(mOrderLists.get(position).getCode());
        tv_xiadan_time.setText(mOrderLists.get(position).getCreateTime());

        ImageHelper.display(ConstantS.BASE_PIC_URL+mOrderLists.get(position).getSamplePic(),iv_new_order);
        tv_new_order_biaoti.setText(mOrderLists.get(position).getSampleName());
        tv_new_order_content.setText(mOrderLists.get(position).getSampleProDetail());
        tv_new_order_jiage.setText("Ks "+mOrderLists.get(position).getSamplePrice());
        tv_new_order_shuliang.setText("×"+mOrderLists.get(position).getSampleNum());
        switch (type){
            case 0:
                ll_order_time.setVisibility(View.GONE);
                break;
            case 1:
                tv_new_order_queding.setVisibility(View.GONE);
                tv_new_order_quxiao.setVisibility(View.GONE);
                break;

        }

        tv_new_order_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type==130){
                    lister(mOrderLists.get(position).getId(),2,position);
                }else{
                lister(mOrderLists.get(position).getId(),0,position);
                }
            }
        });
        tv_new_order_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    lister(mOrderLists.get(position).getId(),1,position);


            }
        });
        //
//        ImageHelper.display();
    }

    @Override
    public void initView(View rootView) {
        tv_new_order_title = (TextView) rootView.findViewById(R.id.tv_new_order_title);
        tv_new_order_num = (TextView) rootView.findViewById(R.id.tv_new_order_num);
        tv_new_order_quxiao = (TextView) rootView.findViewById(R.id.tv_new_order_quxiao);
        tv_new_order_queding = (TextView) rootView.findViewById(R.id.tv_new_order_queding);
        xidanshijian = (TextView) rootView.findViewById(R.id.xidanshijian);
        tv_xiadan_time = (TextView) rootView.findViewById(R.id.tv_xiadan_time);
        rl_order_dianji = (LinearLayout) rootView.findViewById(R.id.rl_order_dianji);
        ll_order_buyes_message = (LinearLayout) rootView.findViewById(R.id.ll_order_buyes_message);
        ll_order_time = (LinearLayout) rootView.findViewById(R.id.ll_order_time);
        tv_order_buyersname = (TextView) rootView.findViewById(R.id.tv_order_buyersname);
        tv_order_contact = (TextView) rootView.findViewById(R.id.tv_order_contact);
        quanbu_money = (TextView) rootView.findViewById(R.id.quanbu_money);
        tv_order_money = (TextView) rootView.findViewById(R.id.tv_order_money);
        tv_new_order_quxiao.setText(mTranslatesString.getCommon_jujue());
        tv_new_order_queding.setText(mTranslatesString.getCommon_aggree());
        iv_new_order = (ImageView) rootView.findViewById(R.id.iv_new_order);
        tv_new_order_biaoti = (TextView) rootView.findViewById(R.id.tv_new_order_biaoti);
        tv_new_order_content = (TextView) rootView.findViewById(R.id.tv_new_order_content);
        tv_new_order_jiage = (TextView) rootView.findViewById(R.id.tv_new_order_jiage);
        tv_new_order_shuliang = (TextView) rootView.findViewById(R.id.tv_new_order_shuliang);
    }
    public abstract void lister (int id,int type,int position);
}
