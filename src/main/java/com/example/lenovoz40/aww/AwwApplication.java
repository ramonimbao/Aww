package com.example.lenovoz40.aww;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lenovo Z40 on 4/28/2016.
 */
public class AwwApplication extends Application {
    private String aBackground = " ";


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
}
