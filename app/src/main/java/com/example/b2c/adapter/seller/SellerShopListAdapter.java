package com.example.b2c.adapter.seller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.adapter.base.XRcycleViewAdapterBase;
import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ImageHelper;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.NoDataListener;
import com.example.b2c.net.response.seller.ShopListBean;
import com.example.b2c.net.response.translate.MobileStaticTextCode;
import com.example.b2c.widget.SellerDataConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2017/4/26.
 */

public abstract class SellerShopListAdapter extends XRcycleViewAdapterBase {
    private Context context;
    private TextView tv_shop_title;
    private TextView tv_shop_num;
    private ImageView iv_shop;
    private TextView tv_shop_biaoti;
    private TextView tv_shop_jiage;
    private TextView tv_shop_kucun;
    private TextView tv_shop_xiajia;
    private TextView tv_shop_delete;
    private TextView tv_shop_xiugai;
    private List<ShopListBean.Rows>rows;
    private MobileStaticTextCode mTranslatesString;
    private SellerDataConnection sellerRdm;
    public SellerShopListAdapter() {
    }

    public SellerShopListAdapter(Context context, List<ShopListBean.Rows>rows,
                                 MobileStaticTextCode mTranslatesString, SellerDataConnection sellerRdm) {
        this.context = context;
        this.rows=rows;
        this.mTranslatesString=mTranslatesString;
        this.sellerRdm=sellerRdm;
    }

    @Override
    public int getItemPostion() {
        return rows.size();
    }

    @Override
    public int getLayoutID() {
        return R.layout.seller_shop_adapter;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void initData(MyViewHolder holder, final int position) {
        tv_shop_title.setText(mTranslatesString.getOrder_no());
        tv_shop_xiugai.setText(mTranslatesString.getCommon_edit());
        tv_shop_xiajia.setText(mTranslatesString.getShop_down());

//        tv_shop_num.setText(rows.get(position).);
        ImageHelper.display(ConstantS.BASE_PIC_URL+rows.get(position).getSamplePic(),iv_shop);
        tv_shop_biaoti.setText(rows.get(position).getName());
        tv_shop_jiage.setText("Ks "+rows.get(position).getPrice());
        tv_shop_kucun.setText(mTranslatesString.getCommon_stock()+":"+rows.get(position).getSampleSKU());
        if (rows.get(position).getIsSelling()==1){
            //此时的状态是上架，所以文字显示要反着来，显示下架
            tv_shop_xiajia.setText(mTranslatesString.getShop_down());
        }else{
            tv_shop_xiajia.setText(mTranslatesString.getShop_up());
        }
        tv_shop_xiajia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rows.get(position).getIsSelling()==1){
                   //进行下架
                    editZhuangtai(1,rows.get(position).getId(),position,tv_shop_xiajia);
                }else{
                   //进行上架
                    editZhuangtai(2,rows.get(position).getId(),position,tv_shop_xiajia);
                }
            }
        });
        tv_shop_xiugai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bianji(rows.get(position).getId());
            }
        });
    }

    @Override
    public void initView(View rootView) {
        tv_shop_title = (TextView) rootView.findViewById(R.id.tv_shop_title);
        tv_shop_num = (TextView) rootView.findViewById(R.id.tv_shop_num);
        iv_shop = (ImageView) rootView.findViewById(R.id.iv_shop);
        tv_shop_biaoti = (TextView) rootView.findViewById(R.id.tv_shop_biaoti);
        tv_shop_jiage = (TextView) rootView.findViewById(R.id.tv_shop_jiage);
        tv_shop_kucun = (TextView) rootView.findViewById(R.id.tv_shop_kucun);
        tv_shop_xiajia = (TextView) rootView.findViewById(R.id.tv_shop_xiajia);
        tv_shop_delete = (TextView) rootView.findViewById(R.id.tv_shop_delete);
        tv_shop_xiugai = (TextView) rootView.findViewById(R.id.tv_shop_xiugai);
    }
    public abstract void bianji(int id);
    public abstract  void editZhuangtai(int type, int id,int position,TextView tv_shop_xiajia);
}
