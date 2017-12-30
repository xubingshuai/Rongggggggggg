package com.ronggggggggg.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weiss on 2016/12/23.
 */
public class Api {

    public static final String BASE_URL = "http://gank.io/api/";
//    public static final String SERVICE_URL = "http://192.168.0.137:8081";
//    public static final String SERVICE_URL = "http://192.168.0.130:8080";
public static final String SERVICE_URL = "http://api.cn.ronghub.com";
    public static final int DEFAULT_TIMEOUT = 10000;

    public Retrofit retrofit;
    public ApiService service;

    //构造方法私有
    private Api() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        File cacheFile = new File(UIUtils.getContext().getCacheDir(), "cache");
//        Cache cache = new Cache(cacheFile, 1024 * 1024 * 20); //20Mb

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
//                .addInterceptor(new SaveCookiesInterceptor())
//                .addInterceptor(new ReadCookiesInterceptor())
//                .addInterceptor(new HttpHeaderInterceptor())
//                .addNetworkInterceptor(new HttpCacheInterceptor())
//                .cache(cache)
                .build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(SERVICE_URL)
                .build();
        service = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final Api INSTANCE = new Api();
    }

    //获取单例
    public static Api getInstance() {
        return SingletonHolder.INSTANCE;
    }


//    class HttpCacheInterceptor implements Interceptor {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetworkUtils.isConnected()) {
//                request = request.newBuilder()
//                        .cacheControl(CacheControl.FORCE_CACHE)
//                        .build();
//                Logger.e("Okhttp " + "no network");
//            }
//
//            Response originalResponse = chain.proceed(request);
//            if (NetworkUtils.isConnected()) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, only-if-cached, max-stale=2419200")
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    }
}