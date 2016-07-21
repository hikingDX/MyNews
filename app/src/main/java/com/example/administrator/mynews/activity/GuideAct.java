package com.example.administrator.mynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.utils.PrefUtils;

import java.util.ArrayList;

public class GuideAct extends Activity {
    private ViewPager vpGuide;
    private LinearLayout llPointGroup;//引导原点的父控件
    private static final int[] mImageIds = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> mImageViewList;
    private View viewRedPoint;//小红点
    private int mPointWidth;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_guide);
        vpGuide = (ViewPager) findViewById(R.id.vp_guide);
        llPointGroup = (LinearLayout) findViewById(R.id.ll_point_group);
        viewRedPoint = findViewById(R.id.view_red_point);
        mBtn = (Button) findViewById(R.id.button1);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新sp
                PrefUtils.setBoolean(GuideAct.this,"is_user_guide_showed",true);
                startActivity(new Intent(GuideAct.this,MainAct.class));
                finish();

            }
        });
        initViews();
        vpGuide.setAdapter(new GuideAdapter());
        vpGuide.setOnPageChangeListener(new GuidePageListener());
        //        vpGuide.addOnPageChangeListener(new GuidePageListener());
    }


    /**
     * 准备数据
     */
    private void initViews() {
        mImageViewList = new ArrayList<ImageView>();

        //初始化引导页的3个页面
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView image = new ImageView(this);
            image.setBackgroundResource(mImageIds[i]);//设置引导页背景
            mImageViewList.add(image);
        }
        //初始化引导页的小圆点
        for (int i = 0; i < mImageIds.length; i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_gray);
            //设置引导原点的大小

            LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(20, 20);
            if (i > 0) {
                mParams.leftMargin = 20;//设置原点间隔
            }
            point.setLayoutParams(mParams);
            llPointGroup.addView(point);//将原点添加个线性布局
        }
        //measure(测量大小) layout(界面位置) ondraw
        //获取视图树
        llPointGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            //当layout执行结束后回调次方法
            @Override
            public void onGlobalLayout() {
                //将监听移除
                llPointGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mPointWidth = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
            }
        });
    }

    class GuidePageListener implements ViewPager.OnPageChangeListener {
        //滑动事件
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            System.out.println("当前位置" + position + "---百分比:" + positionOffset + "---");
            float len = mPointWidth * positionOffset + position * mPointWidth;
            //获取当前红点的布局参数
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) viewRedPoint.getLayoutParams();
            params.leftMargin = (int) len;//设置左边局
            viewRedPoint.setLayoutParams(params);
        }

        //某个页面被选中
        @Override
        public void onPageSelected(int position) {
            if (position == mImageIds.length - 1) {
                mBtn.setVisibility(View.VISIBLE);
            } else {
                mBtn.setVisibility(View.INVISIBLE);
            }
        }

        //滑动状态发生变化
        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }

    /**
     * ViewPager适配器
     */
    class GuideAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageIds.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageViewList.get(position));
            return mImageViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
