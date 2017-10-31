package com.lsw.imageloader;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Luosiwei on 2017/10/31.
 */

public class CloseUtils {
    public static void close(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
