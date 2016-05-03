package com.example.lenovoz40.aww;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class About extends AppCompatActivity {

    LinearLayout llAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        llAbout = (LinearLayout)findViewById(R.id.llAbout);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        llAbout.setBackgroundColor(android.graphics.Color.parseColor(BGColor));
    }
}
