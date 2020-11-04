package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.loginnomer.Models.pinjaman_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;


public class PinjamanActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etIduser, etJenis;
    private EditText etJumlah, etWaktu, etStatus;
    private Button btAjukan;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;
    private DatabaseReference idUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjaman);

        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        etJumlah = findViewById(R.id.etJumlahPin);
        etIduser = findViewById(R.id.etIduser);
        etIduser.setText(user_id);
        etWaktu = findViewById(R.id.etWaktu);
        etJenis = findViewById(R.id.etJenis);
        etStatus = findViewById(R.id.etStatus);
        btAjukan = findViewById(R.id.btAjukanPin);
        btAjukan.setOnClickListener(this);
        radioGroup = findViewById(R.id.opsi);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        etJenis.setText(currentDate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btAjukanPin:
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1){
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                } else {

                }

                String getUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                String getJmlhpinjaman = etJumlah.getText().toString();
                String getJangkawaktu = etWaktu.getText().toString()+" Bulan";
                String getJaminan = selectedRadioButton.getText().toString();
                String getStatus = etStatus.getText().toString();
                String getIduser = etIduser.getText().toString();
                String getJenis = etJenis.getText().toString();
                getReference = database.getReference();

                if (TextUtils.isEmpty(getJaminan) || TextUtils.isEmpty(getJmlhpinjaman)){
                    Toast.makeText(this,"Ada yang kosong loh", Toast.LENGTH_SHORT).show();
                } else {
                    getReference.child("DataPinjaman").push()
                            .setValue(new pinjaman_user(getJmlhpinjaman, getJangkawaktu,
                                    getJaminan, getStatus, getIduser, getJenis))
                            .addOnSuccessListener(this, new OnSuccessListener(){
                                @Override
                                public void onSuccess(Object o) {
                                    etJumlah.setText("");
                                    etWaktu.setText("");
                                    etStatus.setText("Menunggu Konfirmasi");
                                    etJenis.setText("Pinjaman");

                                    Toast.makeText(PinjamanActivity.this,"Bersahil di simpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}
