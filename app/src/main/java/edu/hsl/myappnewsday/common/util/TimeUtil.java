package edu.hsl.myappnewsday.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/3.
 */
public class TimeUtil {

    /**
     * 返回当前时间 并格式化时间格式
     */
    public static String getTime() {
        Date             date       = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(date);
    }

}
