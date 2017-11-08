package com.lsw.imageloader.request;

import com.lsw.imageloader.policy.LoadPolicy;

/**
 * 逆序加载
 * Created by Luosiwei on 2017/11/8.
 */

public class ReversePolicy implements LoadPolicy {
    @Override
    public int compare(BitmapRequest bitmapRequest1, BitmapRequest bitmapRequest2) {
        return bitmapRequest2.serialNum - bitmapRequest1.serialNum;
    }
}
