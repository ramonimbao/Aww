package com.example.lenovoz40.aww;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ImageList extends AppCompatActivity {

    ImageButton btnAbout;
    ListView lvImages;
    ArrayList<CuteImage> alCuteImages;
    CustomAdapter adapCuteImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        btnAbout = (ImageButton)findViewById(R.id.btnAbout);
        lvImages = (ListView)findViewById(R.id.lvImages);

        alCuteImages = new ArrayList<>();
        alCuteImages.add(new CuteImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/272px-Google_2015_logo.svg.png", "RPM"));


        adapCuteImages = new CustomAdapter(this, alCuteImages);
        lvImages.setAdapter(adapCuteImages);

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToAbout = new Intent(ImageList.this, About.class);
                startActivity(goToAbout);
            }
        });
    }
}
