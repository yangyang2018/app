package com.example.b2c.helper;

import android.content.Context;
import android.content.Intent;

/**
 * Created by ThinkPad on 2017/5/5.
 */

public class ShowBigImage {
    public static void showImage(Context context,String url){
        Intent intent = new Intent(context, PicturePreviewActivity.class);
        intent.putExtra("url", url);
        // intent.putExtra("smallPath", getSmallPath());
        intent.putExtra("indentify", getIdentify(context));
        context.startActivity(intent);
    }
    private void test(String url) {

    }
    private static int getIdentify(Context context) {
        return context.getResources().getIdentifier("test", "drawable",
                context.getPackageName());
    }
}
