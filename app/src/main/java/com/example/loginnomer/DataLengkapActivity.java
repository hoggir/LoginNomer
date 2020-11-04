package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginnomer.Models.data_user;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.text.TextUtils.isEmpty;

public class DataLengkapActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etmyNama, etmyAlamatktp, etmyAlamattgl, etmyNohp;
    private Button btSimpanDatareg;
    private DatabaseReference mData_nama, mData_ktp, mData_tgl, mData_nohp;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_lengkap);

        etmyNama = findViewById(R.id.regNama);
        etmyAlamatktp = findViewById(R.id.regAlamatktp);
        etmyAlamattgl = findViewById(R.id.regAlamattgl);
        etmyNohp = findViewById(R.id.regNohp);

        btSimpanDatareg = findViewById(R.id.btSimpanDatareg);
        btSimpanDatareg.setOnClickListener(this);

        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        tvEmail = findViewById(R.id.tvEmail);
        tvEmail.setText(firebaseUser.getEmail());

        mData_nama = FirebaseDatabase.getInstance().getReference().child(user_id).child("DataUser").child("nama");
        mData_ktp = FirebaseDatabase.getInstance().getReference().child(user_id).child("DataUser").child("alamatktp");
        mData_tgl = FirebaseDatabase.getInstance().getReference().child(user_id).child("DataUser").child("alamattgl");
        mData_nohp = FirebaseDatabase.getInstance().getReference().child(user_id).child("DataUser").child("nohp");

        mData_nama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                etmyNama.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_ktp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                etmyAlamatktp.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_tgl.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                etmyAlamattgl.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mData_nohp.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                etmyNohp.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btSimpanDatareg:
                String getUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference getReference;

                final String getNama = etmyNama.getText().toString();
                String getAlamatktp = etmyAlamatktp.getText().toString();
                String getAlamattgl = etmyAlamattgl.getText().toString();
                String getNohp = etmyNohp.getText().toString();

                getReference = database.getReference();

                if(isEmpty(getNama) && isEmpty(getAlamatktp) && isEmpty(getAlamattgl)
                    && isEmpty(getNohp)){
                    Toast.makeText(DataLengkapActivity.this, "Ada yang kosong loh", Toast.LENGTH_SHORT).show();
                } else {
                    getReference.child("DataUser").child(getUserID)
                            .setValue(new data_user(getNama,getAlamatktp,getAlamattgl,getNohp))
                            .addOnSuccessListener(this, new OnSuccessListener(){
                                @Override
                                public void onSuccess(Object o) {
                                    etmyNama.setText("");
                                    etmyAlamatktp.setText("");
                                    etmyAlamattgl.setText("");
                                    etmyNohp.setText("");
                                    Toast.makeText(DataLengkapActivity.this,"Bersahil di simpan", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                break;
        }
    }
}
