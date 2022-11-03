package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class FoodAdapter extends FirebaseRecyclerAdapter <FoodModel, FoodAdapter.myviewholder>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public FoodAdapter(@NonNull FirebaseRecyclerOptions<FoodModel> options) {
        super(options);
    }

    @SuppressLint("RecyclerView")
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder,final int position, @NonNull FoodModel model) {
        holder.txt_gia_dl.setText(model.getGia());
        holder.txt_mota_dl.setText(model.getMota());
        holder.txt_tenfood_dl.setText(model.getName());
        Glide.with(holder.img_dl.getContext()).load(model.getImg()).into(holder.img_dl);
        // show mota cua 1 item
        holder.img_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity= (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new motaDowloadFragment(model.getName(),model.getGia(),model.getMota(),model.getImg())).addToBackStack(null).commit();

            }
        });
        //sua 1 item trong recyclerview
        holder.img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img_dl.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_pop))
                        .setExpanded(true, 1400)
                        .create();

                View view = dialogPlus.getHolderView();
                    //anh xa
                EditText name = view.findViewById(R.id.edt_tenmonan);
                EditText gia    = view.findViewById(R.id.edt_giamonan);
                EditText mota    = view.findViewById(R.id.edt_motamonan);
                EditText img    = view.findViewById(R.id.edt_linkanh);

                Button btn_update = view.findViewById(R.id.btn_update);
                    //gan du lieu
                name.setText(model.getName());
                gia.setText(model.getGia());
                mota.setText(model.getMota());
                img.setText(model.getImg());

                dialogPlus.show();
                btn_update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("gia",gia.getText().toString());
                        map.put("mota",mota.getText().toString());
                        map.put("img",img.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("OrderFood")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.txt_tenfood_dl.getContext(),"Update Thành công ",Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.txt_tenfood_dl.getContext(),"Update Không thành công ",Toast.LENGTH_SHORT).show();
                                    dialogPlus.dismiss();
                                    }
                                });
                    }
                });

            }
        });
        ///xoa 1 item trong recyclerview
        holder.img_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txt_tenfood_dl.getContext());
                builder.setTitle("Bạn có muốn xóa không?");
                builder.setMessage("Dữ liệu xóa, không thể khôi phục!");
                // xoa
                builder.setPositiveButton("Xóa ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("OrderFood")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txt_tenfood_dl.getContext(),"Xóa không thành công! ",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_dong_food,parent,false);
        return new myviewholder(view);
    }

    public  class myviewholder extends RecyclerView.ViewHolder{

        ImageView img_dl, img_edit,img_remove;
        TextView txt_mota_dl, txt_gia_dl, txt_tenfood_dl;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img_dl = itemView.findViewById(R.id.img_dl);
            txt_gia_dl = itemView.findViewById(R.id.txt_gia_dl);
            txt_mota_dl = itemView.findViewById(R.id.txt_mota_dl);
            txt_tenfood_dl = itemView.findViewById(R.id.txt_tenfood_dl);
            img_edit = itemView.findViewById(R.id.img_edit);
            img_remove = itemView.findViewById(R.id.img_remove);

        }
    }
}
