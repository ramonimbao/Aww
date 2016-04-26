package com.example.lenovoz40.aww;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Lenovo Z40 on 4/26/2016.
 * source: http://stackoverflow.com/questions/2471935/how-to-load-an-imageview-by-url-in-android
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    public DownloadImageTask() {

    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon1 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon1 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", "Exception found: " + e.getMessage());
            e.printStackTrace();
        }
        return mIcon1;
    }

    @Override
    protected void onPostExecute(Bitmap result) {

    }
}
