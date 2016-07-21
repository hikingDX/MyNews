package com.example.administrator.mynews.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.utils.PrefUtils;

public class SplashAct extends Activity {

    private RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        startAnim();
    }

    private void startAnim() {
        //动画集合
        AnimationSet set = new AnimationSet(false);
        //旋转动画
        RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);//动画时间
        rotate.setFillAfter(true);//保持动画状态
        //缩放动画
        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(2000);//动画时间
        scale.setFillAfter(true);//保持动画状态
        //渐变动画
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(2000);//动画时间
        alpha.setFillAfter(true);//保持动画状态

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);

        //给动画设置监听
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            /**
             * 动画执行结束执行
             * @param animation
             */
            @Override
            public void onAnimationEnd(Animation animation) {
                jumpNextPage();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rlRoot.startAnimation(set);
    }

    /**
     * 跳转下一个页面
     */
    private void jumpNextPage(){
        //判断之前有没有显示新手引导
        boolean userGuide = PrefUtils.getBoolean(this,"is_user_guide_showed",false);
        if (!userGuide) {
            //跳转主页面
            startActivity(new Intent(SplashAct.this, GuideAct.class));
        } else {
            //跳转主页面
            startActivity(new Intent(SplashAct.this, MainAct.class));
        }
        finish();
    }
}
