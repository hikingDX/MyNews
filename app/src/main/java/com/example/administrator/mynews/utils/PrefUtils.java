package com.example.administrator.mynews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *SharePreference 封装
 *@作者 海境
 *@E-Mail 812385828@qq.com
 *@创建时间 2016/7/20 15:33
 **/
public class PrefUtils {
    public static final  String PREF_NAME = "config";
    public static boolean getBoolean(Context ctx,String key, boolean defaultValue){
        //判断之前有没有显示新手引导
        SharedPreferences sp = ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }
    public static void setBoolean(Context ctx,String key,boolean defaultValue){
        SharedPreferences sp= ctx.getSharedPreferences(PREF_NAME, ctx.MODE_PRIVATE);
        sp.edit().putBoolean(key,defaultValue).commit();
    }
}
