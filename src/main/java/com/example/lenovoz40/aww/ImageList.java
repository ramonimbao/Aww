package com.example.lenovoz40.aww;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        /*
        for(int i=0; i <50 ; i++){
            alCuteImages.add(new CuteImage("https://upload.wikimedia.org/wikipedia/commons/thumb/2/2f/Google_2015_logo.svg/272px-Google_2015_logo.svg.png", "RPM"+ i));
        }
        */

        adapCuteImages = new CustomAdapter(this, alCuteImages);
        lvImages.setAdapter(adapCuteImages);

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(ImageList.this, Settings.class);
                startActivity(goToSettings);
            }
        });

        lvImages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                Toast.makeText(ImageList.this, "Saving...",Toast.LENGTH_SHORT).show();
                String url = alCuteImages.get(i).getRealUrl();

                String modUrl = "";

                if (!url.contains("http://i.imgur.com/")) {
                    // So URL's probably formatted like http://imgur.com/XXXXXX
                    modUrl = "http://i.imgur.com/" + url.substring(17) + ".jpg";
                }

                final String finalUrl = modUrl == "" ? url : modUrl;

                Log.d("DEBUG", "finalURL:" + finalUrl);

                Target target = new Target() {

                    // code from http://stackoverflow.com/questions/25217495/download-images-from-url-to-sd-card
                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                        /*AwwApplication _app = (AwwApplication)getApplication();
                        _app.saveImage(url, bitmap);*/
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                File file = new File(
                                        Environment.getExternalStorageDirectory().getPath()
                                                + "/Aww/" + URLUtil.guessFileName(finalUrl, null, null));
                                try {
                                    file.createNewFile();
                                    FileOutputStream ostream = new FileOutputStream(file);
                                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,ostream);
                                    ostream.close();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                        Toast.makeText(ImageList.this, "Image saved!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onBitmapFailed(Drawable drawable) {
                        Toast.makeText(ImageList.this, "Image saving failed.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPrepareLoad(Drawable drawable) {

                    }
                };

                Picasso.with(ImageList.this).load(finalUrl).into(target);


                return false;
            }
        });

        downloadListFromReddit();
    }

    private void downloadListFromReddit() {
        AsyncTask<Void, String, List<CuteImage>> downloadTask = new AsyncTask<Void, String, List<CuteImage>>() {
            @Override
            protected List<CuteImage> doInBackground(Void... params) {
                AwwApplication app = (AwwApplication)getApplication();
                HttpClient hc = app.getHttpClient();

                String requestUrl = "https://reddit.com/r/aww/.json?limit=100";

                HttpGet apiRequest = new HttpGet(requestUrl);
                apiRequest.setHeader("User-Agent", "AwwAppBrowser");
                List<CuteImage> cuteImages = new ArrayList<CuteImage>();

                try {
                    HttpResponse response = hc.execute(apiRequest);
                    String jsonStr = EntityUtils.toString(response.getEntity());

                    Log.i("JSON", jsonStr);

                    parseListFromReddit(jsonStr, cuteImages);
                } catch(Exception e) {
                    Log.e("ERROR:", "Exception occurred: " + e.getMessage());
                }

                return cuteImages;
            }

            @Override
            protected void onPostExecute(List<CuteImage> cuteImages) {
                alCuteImages.clear();
                alCuteImages.addAll(cuteImages);
                adapCuteImages.notifyDataSetChanged();
                super.onPostExecute(cuteImages);
            }
        };



        downloadTask.execute();
    }

    private void parseListFromReddit(String jsonStr, List<CuteImage> cuteImages) throws JSONException {
        // {root} (object)
        // - {data} (object)
        // --- [children] (array)
        // ----- {n} (from 0 to 99) (object)
        // -------- {data} (object)
        // ---------- {preview} (object)
        // ------------ [images] (array)
        // -------------- {0} (object)
        // ---------------- {source} (object)
        // ------------------ url (param)
        JSONObject rootObj = new JSONObject(jsonStr);
        JSONObject rootData = rootObj.getJSONObject("data");
        JSONArray rootDataArr = rootData.getJSONArray("children");
        for (int i=0; i<100; i++) {
            JSONObject nObj = rootDataArr.getJSONObject(i);
            JSONObject nObjData = nObj.getJSONObject("data");
            String title = nObjData.getString("title");

            String realUrl = nObjData.getString("url");

            JSONObject nObjPreview = nObjData.getJSONObject("preview");
            JSONArray nObjPreviewImages = nObjPreview.getJSONArray("images");
            JSONObject nObjectPreviewIMG0 = nObjPreviewImages.getJSONObject(0); // let's just always grab the first image
            JSONObject nObjectPreviewIMG0Source = nObjectPreviewIMG0.getJSONObject("source");

            String previewUrl = nObjectPreviewIMG0Source.getString("url");

            // check if the URL is a direct link to imgur
            // i.e. URL is something like http://i.imgur.com/XXXXXXX.YYY
            CuteImage cuteImage = new CuteImage(previewUrl, realUrl, title);
            cuteImages.add(cuteImage);
            Log.i("CUTE IMAGE", "Url: " + realUrl);
            Log.i("CUTE IMAGE", "Title: " + title);
        }

    }

    @Override
    protected void onResume() {
        rlImageList = (RelativeLayout)findViewById(R.id.rlImageList);

        AwwApplication app = (AwwApplication) getApplication();
        app.loadBGColor();
        String BGColor = app.getaBackground();

        rlImageList.setBackgroundColor(android.graphics.Color.parseColor(BGColor));
        super.onResume();
    }
}
