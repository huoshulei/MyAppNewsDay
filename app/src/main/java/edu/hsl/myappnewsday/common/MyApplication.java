package edu.hsl.myappnewsday.common;

import android.app.Application;
import android.content.res.Configuration;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/30.
 */
public class MyApplication extends Application {
    private HashMap<String, Object> allData = new HashMap<>();

    public void addAllData(String key, String value) {
        allData.put(key, value);
    }

    public Object getAllData(String key) {
        if (allData.containsKey(key)) {
            return allData.get(key);
        }
        return null;
    }

    public void delAllDataKey(String key) {
        if (allData.containsKey(key)) {
            allData.remove(key);
        }
    }

    public void delAllData() {
        allData.clear();
    }

    public static MyApplication application;

    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        Log.d("onCreate: ");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("onTerminate结束");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("onLowMemory: 内存不足");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("onConfigurationChanged: 配置改变");
    }
}
