package edu.hsl.myappnewsday.common.util;

import android.net.Uri;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * Created by Administrator on 2016/5/31.
 * news_sort?ver=版本号&imei=手机标识符
 * news_list?ver=版本号&subid=分类名&dir=1&nid=新闻id&stamp=20140321&cnt=返回条数
 */
public class UrlUtil {
    String newsData;

    public UrlUtil() {

    }

    /**
     * 拼接地址
     */
    public String getUri(String ver, String subid, String dir, String nid, String stamp,
                         String
                                 cnt) {
        String uri = Uri.parse(CommonUtil.NETPATH_NEWS).buildUpon()
                .appendQueryParameter("ver", ver)
                .appendQueryParameter("subid", subid)
                .appendQueryParameter("dir", dir)
                .appendQueryParameter("nid", nid)
                .appendQueryParameter("stamp", stamp)
                .appendQueryParameter("cnt", cnt)
                .build().toString();
        return uri;
    }

    /**
     * 读取网络数据
     */
    public String getData(String uri) {
        StringBuffer sb = new StringBuffer();

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(uri)
                    .build();
//            String         string      = client.newCall(request).toString();
            Response       response    = client.newCall(request).execute();
            InputStream    inputStream = response.body().byteStream();
            BufferedReader reader      = new BufferedReader(new InputStreamReader(inputStream));
            String         strData;
            while ((strData = reader.readLine()) != null) {
                sb.append(strData);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getNewsData() {

        return newsData;
    }
}
