package com.lsw.imageloader.cache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by Luosiwei on 2017/10/30.
 */

public class MemoryCache implements ImageCache {
    // 图片缓存
    LruCache<String, Bitmap> stringBitmapLruCache;


    public MemoryCache() {
        initImageCache();
    }

    /**
     * 初始化ImageCache
     */
    private void initImageCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        stringBitmapLruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        stringBitmapLruCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        return stringBitmapLruCache.get(url);
    }
}
