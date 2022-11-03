package com.example.myapplication;

public class MainModel  {
    String name, gia , img;

    MainModel(){

    }

    public MainModel(String name, String gia, String img) {
        this.name = name;
        this.gia = gia;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
