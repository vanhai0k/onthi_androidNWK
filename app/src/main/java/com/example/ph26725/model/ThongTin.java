package com.example.ph26725.model;

import com.google.gson.annotations.SerializedName;

public class ThongTin {
    @SerializedName("_id")
    String id;
    String name;
    int tuoi;
    String nganh;
    String image;

    public ThongTin() {
    }

    public ThongTin(String id, String name, int tuoi, String nganh, String image) {
        this.id = id;
        this.name = name;
        this.tuoi = tuoi;
        this.nganh = nganh;
        this.image = image;
    }

    public ThongTin(String name, int tuoi, String nganh, String image) {
        this.name = name;
        this.tuoi = tuoi;
        this.nganh = nganh;
        this.image = image;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
    }
}
