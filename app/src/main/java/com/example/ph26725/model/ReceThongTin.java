package com.example.ph26725.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReceThongTin {
    @SerializedName("data")
    private ArrayList<ThongTin> data;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<ThongTin> getData() {
        return data;
    }

    public void setData(ArrayList<ThongTin> data) {
        this.data = data;
    }
}
