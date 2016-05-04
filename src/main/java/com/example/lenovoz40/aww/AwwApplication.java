package com.example.lenovoz40.aww;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;

/**
 * Created by Lenovo Z40 on 4/28/2016.
 */
public class AwwApplication extends Application {
    private String aBackground = " ";
    private HttpClient httpClient = new DefaultHttpClient();

    public String getaBackground(){
        return aBackground;
    }
    public void setaBackground(String aBackground){
        this.aBackground = aBackground;
    }

    public void saveBGColor(){
        SharedPreferences prefs = getSharedPreferences("AWW_APP", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEdit = prefs.edit();

        prefsEdit.putString("BGCOLOR", aBackground);

        prefsEdit.commit();
        return;
    }
    public void loadBGColor(){
        SharedPreferences prefs = getSharedPreferences("AWW_APP", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEdit = prefs.edit();

        aBackground = prefs.getString("BGCOLOR", "#A1D0C0");
        return;
    }

    public HttpClient getHttpClient() {
        return httpClient;
    }

    public void saveImage(String filename, Bitmap bitmap) {
        if (filename == null || filename.equals(""))
            return;

        if (FSUtil.isStorageReady()) {
            Log.i("INFO","Writing file...");
            // from http://stackoverflow.com/questions/4989182/converting-java-bitmap-to-byte-array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] byteArray = stream.toByteArray();
            FSUtil.write(filename, byteArray);
            Log.i("INFO","...done!");
        }
    }
}
