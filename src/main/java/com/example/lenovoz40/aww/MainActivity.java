package com.example.lenovoz40.aww;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    ImageButton ibLogo;
    ImageButton ibSettings;
    RelativeLayout rlMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ibLogo = (ImageButton) findViewById(R.id.ibLogo);
        ibSettings = (ImageButton) findViewById(R.id.ibSetting);
        rlMain = (RelativeLayout)findViewById(R.id.rlMain);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        rlMain.setBackgroundColor(android.graphics.Color.parseColor(BGColor));

        ibLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToImageList = new Intent(MainActivity.this, ImageList.class);
                startActivity(goToImageList);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(MainActivity.this, Settings.class);

                startActivity(goToSettings);
            }
        });
    }

    @Override
    protected void onResume() {
        rlMain = (RelativeLayout)findViewById(R.id.rlMain);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        rlMain.setBackgroundColor(android.graphics.Color.parseColor(BGColor));
        super.onResume();
    }
}
