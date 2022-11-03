package com.example.myapplication;

public class FoodModel {
    String name, gia, mota, img;

    public FoodModel() {

    }

    public FoodModel(String name, String gia, String mota, String img) {
        this.name = name;
        this.gia = gia;
        this.mota = mota;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
