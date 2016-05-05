package com.example.lenovoz40.aww;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Lenovo Z40 on 4/26/2016.
 */
public class CuteImage {
    private String previewUrl = "";
    private String realUrl = "";
    private String title = "";
    private Bitmap bitmap = null;

    public CuteImage(String previewUrl, String realUrl, String title) {
        this.previewUrl = previewUrl;
        this.realUrl = realUrl;
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }
}
