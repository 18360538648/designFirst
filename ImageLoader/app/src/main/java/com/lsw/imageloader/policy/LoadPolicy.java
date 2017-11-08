package com.lsw.imageloader.policy;

import com.lsw.imageloader.request.BitmapRequest;

/**
 * Created by Luosiwei on 2017/11/8.
 */

public interface LoadPolicy {
    public int compare(BitmapRequest bitmapRequest1, BitmapRequest bitmapRequest2);
}
