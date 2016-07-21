package com.example.administrator.mynews.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.administrator.mynews.R;
import com.example.administrator.mynews.fragment.ContentFrg;
import com.example.administrator.mynews.fragment.LeftMenuFrg;

public class MainAct extends Activity {

    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    private static final String FRAGMENT_CONTENT = "fragment_content";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }

    /**
     * 初始化fragment,将fragment填充给布局文件
     */
    private void initFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//开启事务
        //加TAG便于找到fragment
        transaction.replace(R.id.fl_left_menu,new LeftMenuFrg(),FRAGMENT_LEFT_MENU);//用fragment替换frameLayout
        transaction.replace(R.id.fl_content,new ContentFrg(),FRAGMENT_CONTENT);
        transaction.commit();//提交事务
    }
}


















