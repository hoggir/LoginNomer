package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DaftarActivity extends AppCompatActivity implements View.OnClickListener{
    
    private EditText etmyEmail, etmyPass;
    private Button btReg,btBck;
    private FirebaseAuth auth;
    private String getEmail, getPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etmyEmail = findViewById(R.id.regEmail);
        etmyPass = findViewById(R.id.regPassword);
        auth = FirebaseAuth.getInstance();
        btReg = findViewById(R.id.regUser);
        btReg.setOnClickListener(this);
        btBck = findViewById(R.id.kmblLogin);
        btBck.setOnClickListener(this);
        etmyPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    private void cekDataUser() {
        getEmail = etmyEmail.getText().toString();
        getPass = etmyPass.getText().toString();

        if(TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPass)) {
            Toast.makeText(this,"Ada yang kosong loh!", Toast.LENGTH_SHORT).show();
        } else {
            if(getPass.length() < 6){
                Toast.makeText(this, "Sandi Terlalu Pendek, Minimal 6 Karakter", Toast.LENGTH_SHORT).show();
        } else {
                createUserAccount();
            }
        }
    }

    private void createUserAccount() {
        auth.createUserWithEmailAndPassword(getEmail,getPass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DaftarActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(DaftarActivity.this, "Terjadi Kesalahan, Silakan Coba Lagi", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regUser:
            cekDataUser();
            break;
            case R.id.kmblLogin:
                startActivity(new Intent(DaftarActivity.this, LoginActivity.class));
        }
    }
}
