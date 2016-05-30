package edu.hsl.myappnewsday.common;

/**
 * Created by Administrator on 2016/5/30.
 */
public class Log {
    private static final String  TAG     = "新闻管理log日志";
    public static        boolean isDebug = true;

    public static void d(String tag, String msg) {
        if (isDebug)
            android.util.Log.d(tag, msg);
    }

    public static void d(String msg) {
        if (isDebug)
            android.util.Log.d(TAG, msg);
    }
}
