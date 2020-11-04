package com.example.loginnomer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.loginnomer.Models.pinjaman_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdatePinjamanActivity extends AppCompatActivity {

    private EditText etwaktuBaru, etjumlahBaru, etjenisBaru, etstatusBaru, etiduserBaru;
    private Button btUpdate;
    private DatabaseReference database;
    private FirebaseAuth auth;
    private String cekJumlah, cekWaktu, cekJaminan, cekId, cekStatus, cekJenis;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pinjaman);

        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        etwaktuBaru = findViewById(R.id.etWaktuBaru);
        etjumlahBaru = findViewById(R.id.etJumlahBaru);
        etstatusBaru = findViewById(R.id.etStatusBaru);
        etiduserBaru = findViewById(R.id.etIduserBaru);
        etiduserBaru.setText(user_id);
        etjenisBaru = findViewById(R.id.etIJenisBaru);
        btUpdate = findViewById(R.id.btUpdatePin);

        radioGroup = findViewById(R.id.opsiupdate);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();
        getData();
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);

                cekJumlah = etjumlahBaru.getText().toString();
                cekWaktu = etwaktuBaru.getText().toString();
                cekJaminan = radioButton.getText().toString();
                cekId = etiduserBaru.getText().toString();
                cekStatus = etstatusBaru.getText().toString();
                cekJenis = etjenisBaru.getText().toString();

                if(isEmpty(cekStatus) || isEmpty(cekId) || isEmpty(cekWaktu) || isEmpty(cekJumlah) || isEmpty(cekJaminan)){
                    Toast.makeText(UpdatePinjamanActivity.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else{
                    pinjaman_user setPinjaman= new pinjaman_user();
                    setPinjaman.setJmlhpinjaman(etjumlahBaru.getText().toString());
                    setPinjaman.setJangkawaktu(etwaktuBaru.getText().toString());
                    setPinjaman.setJaminan(radioButton.getText().toString());
                    setPinjaman.setStatus(etstatusBaru.getText().toString());
                    setPinjaman.setIduser(etiduserBaru.getText().toString());
                    setPinjaman.setTglpengajuan(etjenisBaru.getText().toString());
                    updateJaminan(setPinjaman);
                }
            }
        });

    }

    private boolean isEmpty(String s){
        return TextUtils.isEmpty(s);
    }

    private void updateJaminan(pinjaman_user setPinjaman) {
        String userID = auth.getUid();
        String getKey = getIntent().getExtras().getString("getPrimaryKey");
        database.child("DataPinjaman")
                .child(getKey)
                .setValue(setPinjaman)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdatePinjamanActivity.this, "Data Berhasil diubah", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private void getData() {
        final String getJumlah = getIntent().getExtras().getString("dataJumlah");
        final String getWaktu = getIntent().getExtras().getString("dataWaktu");
        final String getStatus = getIntent().getExtras().getString("dataStatus");
        final String getId = getIntent().getExtras().getString("dataIduser");
        final String getJenis = getIntent().getExtras().getString("dataJenis");
        etjenisBaru.setText(getJenis);
        etjumlahBaru.setText(getJumlah);
        etwaktuBaru.setText(getWaktu);
        etiduserBaru.setText(getId);
        etstatusBaru.setText(getStatus);
    }
}
