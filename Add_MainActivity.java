package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Add_MainActivity extends AppCompatActivity {
    Button btn_back,btn_save;
    EditText edt_linkanh, edt_motamonan, edt_giamonan, edt_tenmonan;
    public static boolean status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main);
        //anh xa view
        edt_giamonan = findViewById(R.id.edt_giamonan);
        edt_linkanh = findViewById(R.id.edt_linkanh);
        edt_motamonan = findViewById(R.id.edt_motamonan);
        edt_tenmonan = findViewById(R.id.edt_tenmonan);

        btn_back = findViewById(R.id.btn_back);
        btn_save = findViewById(R.id.btn_save);

        // luu
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inserData();
                clearAll();
            }
        });

        //thoat
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_MainActivity.this,MainActivity.class);
                status = true;
                startActivity(intent);

            }
        });
    }

    private void inserData(){

        Map<String,Object> map = new HashMap<>();
        map.put("name",edt_tenmonan.getText().toString());
        map.put("gia",edt_giamonan.getText().toString());
        map.put("mota",edt_motamonan.getText().toString());
        map.put("img",edt_linkanh.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("OrderFood").push()
                .setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Add_MainActivity.this,"Thêm móm ăn thành công!",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Add_MainActivity.this,"Thêm món ăn thất bại!",Toast.LENGTH_SHORT).show();

                    }
                });
    }

    private  void clearAll(){
        edt_giamonan.setText("");
        edt_tenmonan.setText("");
        edt_motamonan.setText("");
        edt_linkanh.setText("");
    }
}