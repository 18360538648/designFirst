package com.lsw.imageloader.core;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lsw.imageloader.cache.ImageCache;
import com.lsw.imageloader.cache.MemoryCache;
import com.lsw.imageloader.config.DisplayConfig;
import com.lsw.imageloader.config.ImageLoaderConfig;
import com.lsw.imageloader.policy.LoadPolicy;

/**
 * 图片加载类
 * <p>
 * Created by Luosiwei on 2017/10/30.
 */

public class ImageLoader {
    // ImageLoader实例
    private static ImageLoader sInstance;
    // 原先的做法是ImageCache放在ImageLoader类中的，但是根据`单一职责原则`，可用将ImageCache抽出去，这样可以做到解耦，缓存和展示分离
    // 默认为内存缓存
    ImageCache imageCahe = new MemoryCache();
    private ImageLoaderConfig imageLoaderConfig;
    public LoadPolicy mLoadPolicy;

    // 构造函数私有化
    private ImageLoader() {
    }

    public void init(ImageLoaderConfig config) {
        checkConfig(config);
        imageLoaderConfig = config;
        imageCahe = config.mImageCache;
        mLoadPolicy = config.mLoadPolicy;
    }

    private void checkConfig(ImageLoaderConfig config) {
        if (config == null) {
            throw new RuntimeException("config is null");
        }
    }

    // DCl单例
    public static ImageLoader getsInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }

        }
        return sInstance;
    }

    public void displayImage(String url, ImageView imageView) {
        displayImage(url, imageView, null, null);
    }

    public void displayImage(String url, ImageView imageView, DisplayConfig display) {
        displayImage(url, imageView, display, null);
    }

    public void displayImage(String url, ImageView imageView, ImageListener imageListener) {
        displayImage(url, imageView, null, imageListener);
    }

    public void displayImage(String url, ImageView imageView, DisplayConfig display, ImageListener imageListener) {

    }


    public static interface ImageListener {
        public void complete(ImageView imageView, Bitmap bitmap, String url);
    }

    public ImageLoaderConfig getConfig() {
        return imageLoaderConfig;
    }
}
