package com.lsw.imageloader.request;

import com.lsw.imageloader.policy.LoadPolicy;

/**
 * 正顺加载
 * Created by Luosiwei on 2017/11/8.
 */

public class SerialPolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest bitmapRequest1, BitmapRequest bitmapRequest2) {
        return bitmapRequest1.serialNum - bitmapRequest2.serialNum;
    }
}
