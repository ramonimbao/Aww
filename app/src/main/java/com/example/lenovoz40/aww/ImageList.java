package com.example.lenovoz40.aww;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ImageList extends AppCompatActivity {

    ImageButton btnSettings;
    ListView lvImages;
    ArrayList<CuteImage> alCuteImages;
    CustomAdapter adapCuteImages;
    RelativeLayout rlImageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        rlImageList = (RelativeLayout)findViewById(R.id.rlImageList);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        rlImageList.setBackgroundColor(android.graphics.Color.parseColor(BGColor));

        btnSettings = (ImageButton)findViewById(R.id.btnAbout);
        lvImages = (ListView)findViewById(R.id.lvImages);

        alCuteImages = new ArrayList<>();
        for(int i=0; i <50 ; i++){
            alCuteImages.add(new CuteImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/272px-Google_2015_logo.svg.png", "RPM"+ i));
        }


        adapCuteImages = new CustomAdapter(this, alCuteImages);
        lvImages.setAdapter(adapCuteImages);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(ImageList.this, Settings.class);
                startActivity(goToSettings);
            }
        });
    }
}
