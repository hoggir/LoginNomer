package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        CardView lyKeluar = findViewById(R.id.lyLogout);
        lyKeluar.setOnClickListener(this);
        CardView lyData = findViewById(R.id.lyData);
        lyData.setOnClickListener(this);
        CardView lyPinjaman = findViewById(R.id.lyPinjaman);
        lyPinjaman.setOnClickListener(this);
        CardView lyProfile = findViewById(R.id.lyProfile);
        lyProfile.setOnClickListener(this);
        CardView lyTabungan = findViewById(R.id.lyTabungan);
        lyTabungan.setOnClickListener(this);
        CardView lyStatus = findViewById(R.id.lyStatus);
        lyStatus.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();

        authListener =new  FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null){
                    Toast.makeText(DashboardActivity.this, "Logout Success", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authListener != null){
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lyLogout:
                auth.signOut();
                break;

            case R.id.lyPinjaman:
                startActivity(new Intent(DashboardActivity.this, PinjamanActivity.class));
                break;

            case R.id.lyTabungan:
                startActivity(new Intent(DashboardActivity.this, TabunganActivity.class));
                break;

            case R.id.lyStatus:
                startActivity(new Intent(DashboardActivity.this, StatusActivity.class));
                break;

            case R.id.lyProfile:
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
                break;
        }

    }
}
