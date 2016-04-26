package com.example.lenovoz40.aww;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    ArrayList<Color> alColors;
    Spinner spnColors;
    ArrayAdapter<Color> adapColors;

    RelativeLayout rlSettings;

    String backgroundColor = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        rlSettings = (RelativeLayout)findViewById(R.id.rlSettings);

        alColors = new ArrayList<Color>();
        alColors.add(new Color("Beige", "#ECC5A8"));
        alColors.add(new Color("Light blue","#A1D0C0"));
        alColors.add(new Color("White","#FFFFFF"));

        spnColors = (Spinner)findViewById(R.id.spnColors);
        adapColors = new ArrayAdapter<Color>(this, android.R.layout.simple_spinner_dropdown_item, alColors);
        spnColors.setAdapter(adapColors);

        spnColors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                backgroundColor = adapColors.getItem(position).getColor();
                rlSettings.setBackgroundColor(android.graphics.Color.parseColor(backgroundColor));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
