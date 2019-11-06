package com.bw.movie.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: 韩聪聪
 * data: 2019/11/5 09:9:48
 * function:
 */
public class HttpUtil {
    //创建单例模式
    //创建私有静态变量
    private static HttpUtil httpUtil;
    private final Retrofit retrofit;
    private ConnectivityManager connectivityManager;

    //创建私有构造方法
    private HttpUtil(){
        //创建拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //创建ok
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        //创建retrofit
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://172.17.8.100/movieApi/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //创建对外使用的方法
    public static synchronized HttpUtil getInstance(){
        if (httpUtil==null){
            httpUtil=new HttpUtil();
        }
        return httpUtil;
    }
    //创建对外调用的方法
    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }
    //创建网络判断的方法
    public boolean getWangLuoPanduan(Context context){
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo!=null){
            return activeNetworkInfo.isAvailable();
        }
        return false;
    }
}
