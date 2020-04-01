package com.bw.erjiliebiao.utile;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:15:36
 *@Description:
 * */
public class SpUtile {
   public static final String USERINFOR="iseromfor";
   public static final String USERID="userId";
   public static final String SESSIONID="sessionId";
   //存
   @SuppressLint("CommitPrefEdits")
   public static void putString(Context context, String name, String key, String value){

       SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
     //通过sp.edit获取写的方法
       SharedPreferences.Editor edit = sharedPreferences.edit();
       //放传过来的值
       edit.putString(key,value);
       edit.commit();
   }
   //取  通过键来取值
    public static String getString(Context context,String name,String key){
       //获取sp  MODE_PRIVATE私有化模式
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String a = sharedPreferences.getString(key, "a");
        return a;

    }


}
