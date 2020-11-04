package com.example.loginnomer.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginnomer.Models.tabungan_user;
import com.example.loginnomer.R;

import java.util.ArrayList;

public class TabunganRecyclerViewAdapter extends RecyclerView.Adapter<TabunganRecyclerViewAdapter.ViewHolder> {

    private ArrayList<tabungan_user> listTabungan;
    private Context context;

    public TabunganRecyclerViewAdapter(ArrayList<tabungan_user> listTabungan, Context context){
        this.listTabungan = listTabungan;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.tabungan_desgin, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String Jenis = listTabungan.get(position).getJenis();
        final String Status = listTabungan.get(position).getStatus();
        final String JenisP = listTabungan.get(position).getJenispengajuan();

        holder.tvJenis.setText("Jenis Tabungan : "+Jenis);
        holder.tvJenisp.setText(JenisP);
        holder.tvStatus.setText(Status);

        holder.ListItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final String[] action = {"Update", "Delete"};
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTabungan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvJenis, tvStatus, tvJenisp;
        private CardView ListItem;

        ViewHolder(View itemView){
            super(itemView);
            tvJenis = itemView.findViewById(R.id.tvTjenis);
            tvJenisp = itemView.findViewById(R.id.tvTjenisP);
            tvStatus = itemView.findViewById(R.id.tvTstatus);
            ListItem = itemView.findViewById(R.id.list_item_tabungan);
        }
    }


}
