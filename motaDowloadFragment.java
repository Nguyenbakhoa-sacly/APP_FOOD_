package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class motaDowloadFragment extends Fragment {

    String name, gia, mota, img;

    public motaDowloadFragment(String name, String gia, String mota, String img) {

        this.name = name;
        this.gia = gia;
        this.mota = mota;
        this.img = img;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_mota_food, container, false);
        ImageView imgMoTa = view.findViewById(R.id.imgMoTa);
        TextView txt_mota_tenfood = view.findViewById(R.id.txt_mota_tenfood);
        TextView txt_gia_mota = view.findViewById(R.id.txt_gia_mota);
        TextView txt_mo_ta = view.findViewById(R.id.txt_mo_ta);

        txt_mota_tenfood.setText(name);
        txt_gia_mota.setText(gia);
        txt_mo_ta.setText(mota);
        Glide.with(getContext()).load(img).into(imgMoTa);

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity= (AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Food()).addToBackStack(null).commit();

    }

}