package com.lsw.imageloader.cache;

import android.graphics.Bitmap;

/**
 * Created by Luosiwei on 2017/10/30.
 */

public interface ImageCache {
    public void put(String url, Bitmap bitmap);

    public Bitmap get(String url);
}
