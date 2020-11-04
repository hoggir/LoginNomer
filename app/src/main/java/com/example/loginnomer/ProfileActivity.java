package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mData_nama, mData_ktp, mData_tgl, mData_nohp;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView tvEmail, tvNama, tvalamatKTP, tvalamatTinggal,
            tvNoHP;
    public Button btProfile, btah;

    private int RC_SIGN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        btProfile = findViewById(R.id.btProfile);
        btProfile.setOnClickListener(this);
        btah = findViewById(R.id.prf);
        btah.setOnClickListener(this);
        tvNama = findViewById(R.id.tvPNama);
        tvalamatKTP = findViewById(R.id.tvPAlamatKTP);
        tvalamatTinggal = findViewById(R.id.tvPAlamatTinggal);
        tvNoHP = findViewById(R.id.tvPNoHP);

        firebaseAuth =FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tvEmail = findViewById(R.id.tvEmail);
        tvEmail.setText(firebaseUser.getEmail());

        mData_nama = FirebaseDatabase.getInstance().getReference().child("DataUser").child(user_id).child("nama");
        mData_ktp = FirebaseDatabase.getInstance().getReference().child("DataUser").child(user_id).child("alamatktp");
        mData_tgl = FirebaseDatabase.getInstance().getReference().child("DataUser").child(user_id).child("alamattgl");
        mData_nohp = FirebaseDatabase.getInstance().getReference().child("DataUser").child(user_id).child("nohp");

        mData_nama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNama.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_ktp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvalamatKTP.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_tgl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvalamatTinggal.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_nohp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNoHP.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btProfile:
                startActivity(new Intent(ProfileActivity.this, ProfileLengkapActivity.class));
                break;

            case R.id.prf:
                startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
                break;
        }
    }
}
