package com.bw.erjiliebiao.utile;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/*
 *@Auther:cln
 *@Date: 2020/3/26
 *@Time:16:04
 *@Description:
 * */
public class NetUtile {
    //定义所有请求网址的域名
    String BASE_URL="http://mobile.bwstudent.com/small/";
    private Apis mapis;

    private NetUtile(){
        initNetUtile();
    }
    private static class SingleInsetance{
        private static NetUtile INSETANCE=new NetUtile();
    }

    public static NetUtile getInstance() {
        return SingleInsetance.INSETANCE;
    }
    private void initNetUtile() {
        //添加日志拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设置等级
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient client = builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                //添加应用拦截器
                .addInterceptor(httpLoggingInterceptor)
                //创建
                .build();
        //请求数据
        Retrofit.Builder retorfitbuilder = new Retrofit.Builder();
        Retrofit retrofit = retorfitbuilder.baseUrl(BASE_URL)
                .client(client)
                //添加RXJAVA
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mapis = retrofit.create(Apis.class);

    }
    //获取api的方法
    public Apis getApi(){
        return mapis;
    }

}
