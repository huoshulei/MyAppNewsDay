package edu.hsl.myappnewsday.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Administrator on 2016/5/30.
 * 本地储存一些值
 */
public class PreserveUtil {
    static SharedPreferences preferences;

    public static void putString(Context context, String key, String value) {
        preferences = getPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key, value);
        edit.apply();
    }

    public static void putStringSet(Context context, String key, Set<String> value) {
        preferences = getPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putStringSet(key, value);
        edit.apply();
    }

    public static void putBoolean(Context context, String key, boolean value) {
        preferences = getPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key, value);
        edit.apply();
    }

    private static SharedPreferences getPreferences(Context context) {
        if (preferences == null)
            return context.getSharedPreferences("isNews", Context
                    .MODE_PRIVATE);
        return preferences;
    }

    public static boolean getBoolean(Context context, String key) {
        preferences = getPreferences(context);
        return preferences.getBoolean(key, true);
    }

    public static String getString(Context context, String key) {
        preferences = getPreferences(context);
        return preferences.getString(key, null);
    }

    public static Set<String> getStringSet(Context context, String key) {
        preferences = getPreferences(context);
        return preferences.getStringSet(key, null);
    }
//    public static void getStringSe0t(Context context, String key) {
//        SharedPreferences.Editor edit = preferences.edit();
//        edit.putStringSet("",<span style="font-family: Arial, Helvetica, sans-serif;
// ">newReadIdSet </span><span style="font-family: Arial, Helvetica, sans-serif;">);</span>
//                edit.apply();
//
//    }

}
