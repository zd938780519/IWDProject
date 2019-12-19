package com.example.project.iwdproject.RxJavaUtils;


public class RetrofitHttpUtil {
    public static ApiService getApiService() {
        return RetrofitHelper.getRetrofit().create(ApiService.class);
    }
//    public static ApiService getApiServiceShare() {
//        return RetrofitHelper.getShareRetrofit().create(ApiService.class);
//    }
}
