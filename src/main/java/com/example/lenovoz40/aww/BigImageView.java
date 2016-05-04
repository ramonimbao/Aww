package com.example.lenovoz40.aww;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class BigImageView extends AppCompatActivity {

    RelativeLayout rlBigImageView;

    ImageView ivBigImage;
    TextView tvBigTitle;
    ImageButton ibSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_image_view);

        rlBigImageView = (RelativeLayout) findViewById(R.id.rlBigImageView);
        ivBigImage = (ImageView) findViewById(R.id.ivBigImage);
        tvBigTitle = (TextView) findViewById(R.id.tvBigTitle);
        ibSave = (ImageButton) findViewById(R.id.ibSave);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        rlBigImageView.setBackgroundColor(android.graphics.Color.parseColor(BGColor));

        Intent cuteImageIntent = getIntent();
        final String title = cuteImageIntent.getStringExtra("TITLE");
        final String url = cuteImageIntent.getStringExtra("URL");

        tvBigTitle.setText(title);
        Picasso.with(this).load(url).into(ivBigImage);

        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AwwApplication app = (AwwApplication)getApplication();
                // from http://stackoverflow.com/questions/8306623/get-bitmap-attached-to-imageview
                // also http://stackoverflow.com/questions/11575943/parse-file-name-from-url-before-downloading-the-file
                Bitmap bmp = ((BitmapDrawable)ivBigImage.getDrawable()).getBitmap();
                String filename = URLUtil.guessFileName(url, null, null);
                app.saveImage(filename, bmp);
            }
        });
    }
}
