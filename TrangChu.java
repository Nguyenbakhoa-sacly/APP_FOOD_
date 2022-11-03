package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.relex.circleindicator.CircleIndicator;

public class TrangChu extends Fragment {
    //recylerview
    private RecyclerView mrecyclerView;
    private MainAdapter mainAdapter;

    private ViewPager mviewpager;
    private CircleIndicator mCircleIndicator;
    private List<photo> mlistphoto;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if(mviewpager.getCurrentItem() == mlistphoto.size() -1){
                mviewpager.setCurrentItem(0);

            }else{
                mviewpager.setCurrentItem(mviewpager.getCurrentItem() +1);
            }
        }
    };
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

        //anh xa recyclerview
        mrecyclerView= (RecyclerView) view.findViewById(R.id.rv_food);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food"), MainModel.class)
                        .build();
        mainAdapter= new MainAdapter(options);
        mrecyclerView.setAdapter(mainAdapter);

//list img viewpager
        mviewpager = view.findViewById(R.id.view_pager);
        mCircleIndicator = view.findViewById(R.id.circle_center);
        mlistphoto = getListphoto();
        photoViewPagerAdapter adapter = new photoViewPagerAdapter(mlistphoto);
        mviewpager.setAdapter(adapter);
        mCircleIndicator.setViewPager(mviewpager);
        mHandler.postDelayed(mRunnable,3000);
        mviewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable,3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//
        return view;
    }

//viewpager
    private List<photo> getListphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.m1));
        list.add(new photo(R.drawable.m2));
        list.add(new photo(R.drawable.m3));
        list.add(new photo(R.drawable.m4));
        list.add(new photo(R.drawable.m5));
        return list;
    }

    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);

    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable,3000);
    }

    //recyclerview

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
}