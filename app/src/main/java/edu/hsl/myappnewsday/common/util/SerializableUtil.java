package edu.hsl.myappnewsday.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.ArraySet;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Set;

import edu.hsl.myappnewsday.R;
import edu.hsl.myappnewsday.bean.NewsBean;

/**
 * Created by Administrator on 2016/6/15.
 */
public class SerializableUtil {

    static Set<String> nids;

    /**
     * 读取本地保存的数据
     */
    public static Set<String> getNids(Context context, String key) {
        nids = PreserveUtil.getStringSet(context, key);
        if (nids == null)
            nids = new ArraySet<>();
        return nids;
    }

    /**
     * 序列化对象
     */
    public static void serialize(Context context, NewsBean.Data data) {
//        getBitmap(context, data, queue);
        String serStr = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream
                    (byteArrayOutputStream);
            objectOutputStream.writeObject(data);
            serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = URLEncoder.encode(serStr, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            Set<String> nids = getNids(context, "FAVORITE");
            if (nids.contains(serStr))
//            for (String s : nids) {
//                if (s.equals(serStr))
            {
                Toast.makeText(context, "内容已存在无需重复收藏!", Toast.LENGTH_SHORT).show();
                return;
            }
//            }
            nids.add(serStr);
            PreserveUtil.putStringSet(context, "FAVORITE", nids);
            Toast.makeText(context, "收藏成功!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return serStr;
    }

    /**
     * 反序列化
     */
    public static NewsBean.Data deSerialization(String serStr) {
        NewsBean.Data data = null;
        try {
            String redStr = URLDecoder.decode(serStr, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes
                    ("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            data = (NewsBean.Data) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 请求网络图片 缓存到本地以供收藏
     */
    public static void getBitmap(final Context context, final NewsBean.Data data, RequestQueue
            queue) {
        ImageRequest request = new ImageRequest(data.getIcon(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                data.setBitmap(getBytes(response));
                serialize(context, data);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                data.setBitmap(getBytes(BitmapFactory.decodeResource(context.getResources(), R
                        .mipmap.defaultpic)));
                serialize(context, data);
            }
        });
        queue.add(request);
    }

    /**
     * bitmap转换成字节数组 供 序列化使用
     */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 字节数组转换成bitmap
     */
    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
