package edu.hsl.myappnewsday.common.util;

import com.google.gson.Gson;

import edu.hsl.myappnewsday.bean.NewsBean;

/**
 * Created by Administrator on 2016/5/31.
 */
public class JsonUtil {

    public NewsBean getNewsBean(String json) {
        Gson     gson     = new Gson();
        NewsBean newsBean = gson.fromJson(json, NewsBean.class);
        return newsBean;
    }
}
