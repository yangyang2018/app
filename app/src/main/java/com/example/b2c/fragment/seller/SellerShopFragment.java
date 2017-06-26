package com.example.b2c.fragment.seller;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempleBaseFragment;

import com.example.b2c.config.ConstantS;
import com.example.b2c.helper.ToastHelper;
import com.example.b2c.net.listener.base.PageListListener;
import com.example.b2c.net.response.seller.SellerShopTabBean;
import com.example.b2c.widget.magicindicator.ClipPagerTitleView;
import com.example.b2c.widget.magicindicator.CommonNavigator;
import com.example.b2c.widget.magicindicator.CommonNavigatorAdapter;
import com.example.b2c.widget.magicindicator.FragmentContainerHelper;
import com.example.b2c.widget.magicindicator.IPagerIndicator;
import com.example.b2c.widget.magicindicator.IPagerTitleView;
import com.example.b2c.widget.magicindicator.LinePagerIndicator;
import com.example.b2c.widget.magicindicator.MagicIndicator;
import com.example.b2c.widget.magicindicator.UIUtil;

import java.util.ArrayList;
import java.util.List;

/**
商品的fragment
 */
public class SellerShopFragment extends TempleBaseFragment {
    private List<String> leimuName;
    private List<TempleBaseFragment> mDataList;
    private List<SellerShopTabBean.Rows> leiMu;
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    private CommonNavigator mCommonNavigator;

    public SellerShopFragment(){}
    @Override
    protected int getContentViewId() {
        return R.layout.fragment_staff_work;
    }
    @Override
    public void initView(View view) {

        leimuName = new ArrayList<>();
        mDataList = new ArrayList<>();
        requestTable(view);




    }
    private void requestTable(final View view){
        showLoading();
        sellerRdm.getShopTable(getActivity(), ConstantS.BASE_URL + "seller/sample/categoryList.htm");
        sellerRdm.mPageListListener = new PageListListener() {
            @Override
            public void onSuccess(List list) {
                leiMu = list;
                for (int i=0;i<leiMu.size();i++){
                    leimuName.add(leiMu.get(i).getName());
                    mDataList.add(new MyFabuShopFragment(leiMu.get(i).getId()));
                }
                initMagicIndicator1(view);
                mFragmentContainerHelper.handlePageSelected(0, true);
                switchPages(0);
            }

            @Override
            public void onError(int errorNO, String errorInfo) {
                biaoji=errorNO;
                ToastHelper.makeToast(errorInfo);
            }

            @Override
            public void onFinish() {
            }

            @Override
            public void onLose() {
            }
        };

    }
    private void initMagicIndicator1(View view) {
        MagicIndicator mMagicIndicator = (MagicIndicator) view.findViewById(R.id.magic_indicator1);
        mMagicIndicator.setBackgroundColor(Color.parseColor("#ffffff"));
        mCommonNavigator = new CommonNavigator(getActivity());
        mCommonNavigator.setSkimOver(true);
        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return leimuName.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ClipPagerTitleView clipPagerTitleView = new ClipPagerTitleView(context);
                clipPagerTitleView.setText(leimuName.get(index));
                clipPagerTitleView.setTextColor(Color.parseColor("#666666"));
                clipPagerTitleView.setClipColor(Color.parseColor("#5bbaab"));
                clipPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        switchPages(index);
                    }
                });
                return clipPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#5bbaab"));
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(mCommonNavigator);
        mFragmentContainerHelper.attachMagicIndicator(mMagicIndicator);
    }
    private void switchPages(int index) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        TempleBaseFragment fragment;
        for (int i = 0, j = mDataList.size(); i < j; i++) {
            if (i == index) {
                continue;
            }
            fragment = mDataList.get(i);
            if (fragment.isAdded()) {
                fragmentTransaction.hide(fragment);
            }
        }
        fragment = mDataList.get(index);
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }
    private int biaoji;

}
