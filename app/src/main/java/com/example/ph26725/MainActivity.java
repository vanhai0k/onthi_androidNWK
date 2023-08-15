package com.example.ph26725;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ph26725.adapter.ThongtinUser;
import com.example.ph26725.api.ApiTT;
import com.example.ph26725.model.ReceThongTin;
import com.example.ph26725.model.ThongTin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<ThongTin> list;
    RecyclerView rcv;
    ThongtinUser adapter;
    FloatingActionButton floadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv = findViewById(R.id.rcv);
        floadd = findViewById(R.id.floadd);

        LinearLayoutManager manager = new LinearLayoutManager(getBaseContext());
        rcv.setLayoutManager(manager);

        list = new ArrayList<>();

        hienthiData();
        floadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd();
            }
        });

    }
    private void hienthiData() {

        ApiTT.apiTt.getData().enqueue(new Callback<ReceThongTin>() {
            @Override
            public void onResponse(Call<ReceThongTin> call, Response<ReceThongTin> response) {
                list = response.body().getData();
                adapter = new ThongtinUser(list,getBaseContext());
                rcv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ReceThongTin> call, Throwable t) {

            }
        });


    }
    private void dialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_add,null);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        TextView tvtitle;
        tvtitle =view.findViewById(R.id.tvtitle);
        tvtitle.setText("Them san pham");

        EditText edname,edtuoi,ednganh,edlink;
        Button btnadd,btndong;
        edlink = view.findViewById(R.id.edlink);
        edtuoi = view.findViewById(R.id.edtuoi);
        ednganh = view.findViewById(R.id.ednganh);
        edname = view.findViewById(R.id.edname);
        btnadd = view.findViewById(R.id.btnadd);
        btndong = view.findViewById(R.id.btndong);



        btnadd.setText("Them User");

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ten = edname.getText().toString().trim();
                int tuoi = Integer.parseInt(edtuoi.getText().toString().trim());
                String nganh = ednganh.getText().toString().trim();
                String anh = edlink.getText().toString().trim();

                ApiTT.apiTt.postData(new ThongTin(ten,tuoi,nganh,anh)).enqueue(new Callback<ThongTin>() {
                    @Override
                    public void onResponse(Call<ThongTin> call, Response<ThongTin> response) {
                        Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                        hienthiData();
                        adapter.notifyDataSetChanged();
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
}