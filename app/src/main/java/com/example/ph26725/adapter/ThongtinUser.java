package com.example.ph26725.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ph26725.R;
import com.example.ph26725.api.ApiTT;
import com.example.ph26725.model.ThongTin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThongtinUser extends RecyclerView.Adapter<ThongtinUser.ThongTinViewHolder> {
    ArrayList<ThongTin> list;
    Context context;

    public ThongtinUser(ArrayList<ThongTin> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public ThongTinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam,parent,false);
        return new ThongTinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinViewHolder holder, int position) {
        ThongTin tt = list.get(position);

        holder.name.setText(tt.getName());
        holder.tuoi.setText(tt.getTuoi()+"");
        holder.nganh.setText(tt.getNganh());

        Glide.with(context)
                .load(tt.getImage())
                .into(holder.image);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                builder.setTitle("Thong bao");
                builder.setMessage("Ban mua xoa nguoi dung nay");
                builder.setNegativeButton("Xoa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = tt.getId();

                        ApiTT.apiTt.deleteData(id).enqueue(new Callback<ThongTin>() {
                            @Override
                            public void onResponse(Call<ThongTin> call, Response<ThongTin> response) {
                                list.remove(position);
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ThongTin> call, Throwable t) {

                            }
                        });
                    }
                });
                builder.setPositiveButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.dialog_add,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                TextView tvtitle;
                tvtitle =view.findViewById(R.id.tvtitle);
                tvtitle.setText("Cap nhap san pham");

                EditText edname,edtuoi,ednganh,edlink;
                Button btnadd,btndong;
                edlink = view.findViewById(R.id.edlink);
                edtuoi = view.findViewById(R.id.edtuoi);
                ednganh = view.findViewById(R.id.ednganh);
                edname = view.findViewById(R.id.edname);
                btnadd = view.findViewById(R.id.btnadd);
                btndong = view.findViewById(R.id.btndong);

                edname.setText(tt.getName());
                ednganh.setText(tt.getNganh());
                edtuoi.setText(tt.getTuoi()+"");
                edlink.setText(tt.getImage());

                btnadd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String id = tt.getId();
                        String name = edname.getText().toString().trim();
                        int tuoi = Integer.parseInt(edtuoi.getText().toString().trim());
                        String nganh = ednganh.getText().toString().trim();
                        String anh = edlink.getText().toString().trim();

                        ApiTT.apiTt.putData(id, new ThongTin(name,tuoi,nganh,anh)).enqueue(new Callback<ThongTin>() {
                            @Override
                            public void onResponse(Call<ThongTin> call, Response<ThongTin> response) {
                                list.set(holder.getAdapterPosition(), new ThongTin(id,name,tuoi,nganh,anh));
                                notifyDataSetChanged();
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<ThongTin> call, Throwable t) {

                            }
                        });
                    }
                });

                btndong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();

            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext());
                View view = LayoutInflater.from(context).inflate(R.layout.infomation,null);
                builder.setView(view);
                AlertDialog dialog = builder.create();

                TextView tvname,tvnganh,tvtuoi;
                ImageView image,thoat;

                tvname = view.findViewById(R.id.tvname);
                tvnganh = view.findViewById(R.id.tvnganh);
                tvtuoi = view.findViewById(R.id.tvtuoi);
                image = view.findViewById(R.id.image);
                thoat = view.findViewById(R.id.thoat);

                tvname.setText(tt.getName());
                tvnganh.setText(tt.getNganh());
                tvtuoi.setText(tt.getTuoi()+"");

                Glide.with(context)
                        .load(tt.getImage())
                        .into(image);

                thoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThongTinViewHolder extends RecyclerView.ViewHolder {
        TextView name, tuoi, nganh, delete, update;
        ImageView image;

        public ThongTinViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            tuoi = itemView.findViewById(R.id.tuoi);
            image = itemView.findViewById(R.id.image);
            nganh = itemView.findViewById(R.id.nganh);
            delete = itemView.findViewById(R.id.delete);
            update = itemView.findViewById(R.id.update);

        }
    }
}
