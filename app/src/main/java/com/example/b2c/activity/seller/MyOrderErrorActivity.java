package com.example.b2c.activity.seller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.example.b2c.R;
import com.example.b2c.activity.common.TempBaseActivity;
import com.example.b2c.activity.common.TempleBaseFragment;
import com.example.b2c.adapter.MyFragmentPagerAdapter;
import com.example.b2c.fragment.seller.MyRefuseWaitingFragment;
import com.example.b2c.widget.LazyViewPager;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * 拒绝订单跟退货的activity
 */
public abstract class MyOrderErrorActivity extends TempBaseActivity implements View.OnClickListener {

    private TextView tv_seller_order_daichuli;
    private TextView tv_seller_order_yichuli;
    private View seller_indicate_line;
    private LazyViewPager seller_order_viewpager;
    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    private List<TempleBaseFragment> fragments;
    private int screenWidth;
    protected abstract int zuoType();
    protected abstract int youType();
    protected abstract TempleBaseFragment fragment(int type);

    /**
     * 标题栏的显示文字
     * @return
     */
    protected abstract String getActivityTitle();
    @Override
    public int getRootId() {
        return R.layout.activity_my_refuse_goods_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        initLinter();
    }

    private void initView() {
        tv_seller_order_daichuli = (TextView) findViewById(R.id.tv_seller_order_daichuli);
        tv_seller_order_yichuli = (TextView) findViewById(R.id.tv_seller_order_yichuli);
        seller_indicate_line = (View) findViewById(R.id.seller_indicate_line);
        seller_order_viewpager = (LazyViewPager) findViewById(R.id.seller_order_viewpager);
        tv_seller_order_daichuli.setText(mTranslatesString.getSeller_waiteorder());
        tv_seller_order_yichuli.setText(mTranslatesString.getSeller_refusing());

        tv_seller_order_daichuli.setOnClickListener(this);
        tv_seller_order_yichuli.setOnClickListener(this);


        ViewPropertyAnimator.animate(tv_seller_order_daichuli).scaleX(1.2f).scaleY(1.2f);
        seller_order_viewpager.setOffscreenPageLimit(0);

        setTitle(getActivityTitle());
    }
    private void initData(){
        fragments = new ArrayList<TempleBaseFragment>();
        fragments.add(fragment(zuoType()));
        fragments.add(fragment(youType()));
        initIndiateWidth();

    }
    private void initLinter(){

        //设置适配器
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        seller_order_viewpager.setAdapter(myFragmentPagerAdapter);

        seller_order_viewpager.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                /**
                 * 偏移距离：百分比*指示器的宽度
                 * 起始位置：position*指示器的宽度
                 * 最终位置：起始位置+偏移距离
                 */
                float offsetX = positionOffset * (seller_indicate_line.getWidth());
                float startX = position * seller_indicate_line.getWidth();
//                float startX = position * screenWidth / fragments.size() + screenWidth / fragments.size() / 4;
                float endX = startX + offsetX;
                ViewHelper.setTranslationX(seller_indicate_line, endX);
            }

            @Override
            public void onPageSelected(int position) {
                //标签颜色改变
                //获取颜色
                int green = getResources().getColor(R.color.hand);
                int halfwhite = getResources().getColor(R.color.text_dark);
                tv_seller_order_daichuli.setTextColor(position == 0 ? green : halfwhite);
                tv_seller_order_yichuli.setTextColor(position == 1 ? green : halfwhite);

                //标签做缩放动画  (animate()要做动画的控件)
                ViewPropertyAnimator.animate(tv_seller_order_daichuli).scaleX(position == 0 ? 1.2f : 1.0f).scaleY(position == 0 ? 1.2f : 1.0f);
                ViewPropertyAnimator.animate(tv_seller_order_yichuli).scaleX(position == 1 ? 1.2f : 1.0f).scaleY(position == 1 ? 1.2f : 1.0f);

                //将现在的position存到本地
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_seller_order_daichuli:
                seller_order_viewpager.setCurrentItem(0);
                break;
            case R.id.tv_seller_order_yichuli:
                seller_order_viewpager.setCurrentItem(1);
                break;
        }
    }
    /**
     * 初始化Indicator的宽度：屏幕的一半
     */

    private void initIndiateWidth() {
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        seller_indicate_line.getLayoutParams().width = screenWidth / fragments.size();
        ViewHelper.setTranslationX(seller_indicate_line, 0);
        //刷新：
        seller_indicate_line.invalidate();//onDraw()
    }


}
