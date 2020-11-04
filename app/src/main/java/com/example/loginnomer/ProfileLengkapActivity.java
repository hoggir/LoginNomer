package com.example.loginnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginnomer.Models.data_lengkap_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.text.TextUtils.isEmpty;

public class ProfileLengkapActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvEmail;
    private EditText etNama, etAlamatKTP, etAlamattgl, etNoHP, etKosong;
    private Button btSimpan;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_lengkap);
        tvEmail = findViewById(R.id.tvPLEmail);

        etNama = findViewById(R.id.etPLNama);
        etAlamatKTP = findViewById(R.id.etPLAlamatktp);
        etAlamattgl = findViewById(R.id.etPLAlamattinggal);
        etNoHP = findViewById(R.id.etPLNoHP);
        etKosong = findViewById(R.id.etPLKosong);
        btSimpan = findViewById(R.id.btPLSimpan);
        btSimpan.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tvEmail.setText(firebaseUser.getEmail());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPLSimpan:
                String getUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                final String getNama = etNama.getText().toString();
                String getAlamatKTP = etAlamatKTP.getText().toString();
                String getAlamatTGL = etAlamattgl.getText().toString();
                String getNoHP = etNoHP.getText().toString();
                String getTempatLahir = etKosong.getText().toString();
                String getTGLLahir = etKosong.getText().toString();
                String getJenisKel = etKosong.getText().toString();
                String getStatus = etKosong.getText().toString();
                String getIdentitas = etKosong.getText().toString();
                String getNoIdentitas = etKosong.getText().toString();
                String getAgama = etKosong.getText().toString();
                String getNamaIbu = etKosong.getText().toString();
                String getDusun = etKosong.getText().toString();
                String getKelurahan = etKosong.getText().toString();
                String getKecamatan = etKosong.getText().toString();
                String getKabupaten = etKosong.getText().toString();
                String getProvinsi = etKosong.getText().toString();
                String getKodePos = etKosong.getText().toString();
                String getEmail = etKosong.getText().toString();
                String getPekerjaan = etKosong.getText().toString();
                String getPendidikan = etKosong.getText().toString();
                String getWargaNegara = etKosong.getText().toString();
                String getNamaPasangan = etKosong.getText().toString();
                String getPekerjaanPasangan = etKosong.getText().toString();
                String getTGLLahirPasangan = etKosong.getText().toString();
                String getIdentitasPasangan = etKosong.getText().toString();
                String getNoIdentitasPasangan = etKosong.getText().toString();

                getReference = database.getReference();

                if (isEmpty(getNama) && isEmpty(getAlamatKTP) && isEmpty(getAlamatTGL) && isEmpty(getNoHP)){
                    Toast.makeText(ProfileLengkapActivity.this,"Tidak Boleh Ada Yang Kosong!", Toast.LENGTH_SHORT).show();
                } else {
                    getReference.child("DataUser").child(getUserID)
                            .setValue(new data_lengkap_user(getNama,getTempatLahir,getTGLLahir,
                                    getJenisKel,getStatus,getIdentitas,getNoIdentitas,getAgama,
                                    getNamaIbu,getAlamatKTP,getDusun,getKelurahan,getKecamatan,getKabupaten,
                                    getProvinsi,getKodePos,getNoHP,getEmail,getPekerjaan,getPendidikan,
                                    getWargaNegara,getNamaPasangan,getPekerjaanPasangan,getTGLLahirPasangan,
                                    getIdentitasPasangan,getNoIdentitasPasangan,getAlamatTGL))
                            .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(ProfileLengkapActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}
