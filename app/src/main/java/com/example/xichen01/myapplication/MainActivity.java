package com.example.xichen01.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ColorPickerView cpvPickerColor;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cpvPickerColor = (ColorPickerView) findViewById(R.id.cpv_color_picker);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_1) {
            cpvPickerColor.setPickColor(Color.parseColor("#FF0000"));
        } else if (id == R.id.btn_2) {
            cpvPickerColor.setPickColor(Color.parseColor("#00FF00"));
        } else if (id == R.id.btn_3) {
            cpvPickerColor.setPickColor(Color.parseColor("#0000FF"));
        } else if (id == R.id.btn_4) {
            cpvPickerColor.setPickColor(Color.parseColor("#ae99dd"));
        }
    }
}
