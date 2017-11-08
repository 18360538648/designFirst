package com.lsw.imageloader.config;


import com.lsw.imageloader.cache.ImageCache;
import com.lsw.imageloader.cache.MemoryCache;
import com.lsw.imageloader.policy.LoadPolicy;
import com.lsw.imageloader.request.SerialPolicy;

/**
 * ImageLoader配置类，build模式的使用
 * 一步一步创建一个复杂对象的创建型模式，它允许用户在不知道内部构建细节的情况下，可以更精细地控制对象的构造过程
 * Created by Luosiwei on 2017/11/8.
 */

public class ImageLoaderConfig {
    public DisplayConfig displayConfig = new DisplayConfig();
    public ImageCache mImageCache = new MemoryCache();
    public int threadCount = Runtime.getRuntime().availableProcessors() + 1;
    public LoadPolicy mLoadPolicy = new SerialPolicy();

    /**
     * 设置加载图片线程数
     *
     * @param count
     * @return
     */
    public ImageLoaderConfig setThread(int count) {
        threadCount = Math.max(1, count);
        return this;
    }

    /**
     * 设置缓存模式
     *
     * @param imageCache
     * @return
     */
    public ImageLoaderConfig setCache(ImageCache imageCache) {
        mImageCache = imageCache;
        return this;
    }

    /**
     * 加载图片占位符
     *
     * @param res
     * @return
     */
    public ImageLoaderConfig setLoadingPlaceholder(int res) {
        displayConfig.loadingRes = res;
        return this;
    }

    /**
     * 加载失败图片占位符
     *
     * @param res
     * @return
     */
    public ImageLoaderConfig setErrorPlaceholder(int res) {
        displayConfig.errorRes = res;
        return this;
    }

    public ImageLoaderConfig setPolicy(LoadPolicy loadPolicy) {
        this.mLoadPolicy = loadPolicy;
        return this;
    }
}
