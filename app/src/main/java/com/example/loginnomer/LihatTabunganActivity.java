package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.loginnomer.Adapter.TabunganRecyclerViewAdapter;
import com.example.loginnomer.Models.tabungan_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatTabunganActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference;
    private ArrayList<tabungan_user> dataTabungan;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_tabungan);

        recyclerView = findViewById(R.id.datalistT);
        auth = FirebaseAuth.getInstance();
        MyRecyclerView();
        GetData();
    }

    private void GetData() {
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("DataTabungan").orderByChild("iduser").equalTo(auth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataTabungan = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            tabungan_user tabungan = snapshot.getValue(tabungan_user.class);

                            tabungan.setKey(snapshot.getKey());
                            dataTabungan.add(tabungan);
                        }

                        adapter = new TabunganRecyclerViewAdapter(dataTabungan, LihatTabunganActivity.this);
                        recyclerView.setAdapter(adapter);

                        Toast.makeText(getApplicationContext(),"Data dimuat", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(),"Data Gagal", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void MyRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        //itemDecoration.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.line));
        //recyclerView.addItemDecoration(itemDecoration);
    }
}
