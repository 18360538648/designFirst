package com.lsw.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片加载类
 * Created by Luosiwei on 2017/10/30.
 */

public class ImageLoader {
    // 原先的做法是ImageCache放在ImageLoader类中的，但是根据`单一职责原则`，可用将ImageCache抽出去，这样可以做到解耦，缓存和展示分离
    // 默认为内存缓存
    ImageCache imageCahe = new MemoryCache();
    // 线程池，线程池的数量为CPU的数量
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    //这里如果设置type类型或者简单的boolean，以后的扩展性就没有，就要修改内部代码了
    // 通过设置各个类实现于某个接口，这样就可以找到共同特点，可以共同操作
    public void setImageCahe(ImageCache imageCahe) {
        this.imageCahe = imageCahe;
    }

    /**
     * 展示图片
     *
     * @param url       图片地址
     * @param imageView 图片对象
     */
    public void displayImage(String url, ImageView imageView) {
        Bitmap bitmap = imageCahe.get(url);
        if (null != bitmap) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        sumbitRequestImg(url, imageView);
    }

    public void sumbitRequestImg(final String url, final ImageView imageView) {
        imageView.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (imageView.getTag().equals(url)) {
                    imageView.setImageBitmap(bitmap);
                }
                imageCahe.put(url, bitmap);
            }
        });
    }

    /**
     * 下载图片
     *
     * @param imgUrl 图片地址
     * @return
     */
    public Bitmap downloadImage(String imgUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
