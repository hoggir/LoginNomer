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

import com.example.loginnomer.Models.tabungan_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TabunganActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etIduser, etStatus, etJenis;
    private Button btTabungan;
    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabungan);

        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        etIduser = findViewById(R.id.tbUserid);
        etIduser.setText(user_id);
        etStatus = findViewById(R.id.tbStatus);
        etJenis = findViewById(R.id.tbJenis);
        etJenis.setText("Tabungan");
        btTabungan = findViewById(R.id.tbAjukan);
        btTabungan.setOnClickListener(this);
        radioGroup = findViewById(R.id.RBTabungan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tbAjukan:
                int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId != -1){
                    selectedRadioButton = findViewById(selectedRadioButtonId);
                } else {

                }

                String getUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getreference;

                String getJenis = selectedRadioButton.getText().toString();
                String getIduser = etIduser.getText().toString();
                String getStatus = etStatus.getText().toString();
                String getJenispengajuan = etJenis.getText().toString();
                getreference = database.getReference();

                if (TextUtils.isEmpty(getUserId)){
                    Toast.makeText(this, "Tidak boleh kosong ya", Toast.LENGTH_SHORT).show();
                } else {
                    getreference.child("DataTabungan").push()
                            .setValue(new tabungan_user(getJenis, getStatus, getIduser, getJenispengajuan))
                            .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    etStatus.setText("Menunggu Konfirmasi");

                                    Toast.makeText(TabunganActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                break;
        }

    }
}
