package com.example.b2c.helper;

import android.os.Handler;
import android.os.Looper;

/**
 * 线程帮助类
 */
public final class ThreadHelper {

    public final static Handler MAIN = new Handler(Looper.getMainLooper());

    public static boolean inMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }


    public static void postMain(Runnable runnable) {
        MAIN.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long delayMillis) {
        MAIN.postDelayed(runnable, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable) {
        MAIN.removeCallbacks(runnable);
    }

}
