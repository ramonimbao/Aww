package com.example.lenovoz40.aww;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by Lenovo Z40 on 4/26/2016.
 */
public class CuteImage {
    private String url = "";
    private String title = "";
    private Bitmap bitmap = null;

    public CuteImage(String url, String title) {
        this.url = url;
        this.title = title;
        this.bitmap = new DownloadImageTask().doInBackground(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        this.bitmap = new DownloadImageTask().doInBackground(url);
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
}
