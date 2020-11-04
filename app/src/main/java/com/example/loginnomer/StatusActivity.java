package com.example.loginnomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StatusActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView cvPinjaman, cvTabungan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        cvPinjaman = findViewById(R.id.stPinjaman);
        cvPinjaman.setOnClickListener(this);
        cvTabungan = findViewById(R.id.stTabungan);
        cvTabungan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.stPinjaman:
                startActivity(new Intent(StatusActivity.this, LihatPinjamanActivity.class));
                break;
            case R.id.stTabungan:
                startActivity(new Intent(StatusActivity.this, LihatTabunganActivity.class));
                break;
        }
    }
}
