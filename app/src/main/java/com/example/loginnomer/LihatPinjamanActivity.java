package com.example.loginnomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginnomer.Adapter.PinjamanRecyclerViewAdapter;
import com.example.loginnomer.Models.pinjaman_user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class LihatPinjamanActivity extends AppCompatActivity {

    private TextView tvId, tvNama;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private DatabaseReference reference, mData_nama, mTotal;
    private ArrayList<pinjaman_user> dataPinjaman;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_pinjaman);
        recyclerView = findViewById(R.id.datalist);
        //getSupportActionBar().setTitle("Data Pinjaman");
        auth = FirebaseAuth.getInstance();
        tvId = findViewById(R.id.lpidu);
        tvNama = findViewById(R.id.lpnama);

        final String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mData_nama = FirebaseDatabase.getInstance().getReference().child("DataUser").child(user_id).child("nama");

        mData_nama.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tvNama.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        MyRecyclerView();
        GetData();
    }

    private void GetData() {
        Toast.makeText(getApplicationContext(), "Mohon Tunggu Sebentar...", Toast.LENGTH_LONG).show();
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("DataPinjaman").orderByChild("iduser").equalTo(auth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataPinjaman = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                            pinjaman_user pinjaman = snapshot.getValue(pinjaman_user.class);

                            pinjaman.setKey(snapshot.getKey());
                            dataPinjaman.add(pinjaman);
                        }

                        adapter = new PinjamanRecyclerViewAdapter(dataPinjaman, LihatPinjamanActivity.this);
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
