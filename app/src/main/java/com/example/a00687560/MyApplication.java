package com.example.a00687560;

import android.content.Context;

/**
 * Created by xpf on 2017/5/1 :)
 * GitHub:xinpengfei520
 * Function:
 */

public class MyApplication extends org.litepal.LitePalApplication {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    // 获取全局上下文
    public static Context getContext() {
        return mContext;
    }
}
