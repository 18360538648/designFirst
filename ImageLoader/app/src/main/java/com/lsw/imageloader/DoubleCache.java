package com.lsw.imageloader;

import android.graphics.Bitmap;

/**
 * Created by Luosiwei on 2017/10/30.
 */

public class DoubleCache implements ImageCache {
    DishCache dishCache = new DishCache();
    MemoryCache memoryCache = new MemoryCache();

    @Override
    public void put(String url, Bitmap bitmap) {
        memoryCache.put(url, bitmap);
        dishCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap = null;
        bitmap = memoryCache.get(url);
        if (null == bitmap) {
            bitmap = dishCache.get(url);
        }
        return bitmap;
    }
}
