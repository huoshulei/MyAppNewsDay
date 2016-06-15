package edu.hsl.myappnewsday.common.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * Created by Administrator on 2016/6/5.
 * 这是个 异步加载图片的辅助类 内存最大占用为10M图片
 */
public class BitmpCacheUtil implements ImageCache {
    private LruCache<String, Bitmap> mCache;

    public BitmpCacheUtil() {
        int maxSize = 10 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
