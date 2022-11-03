package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class photoViewPagerAdapter extends PagerAdapter {

    private List<photo> mlistphoto;

    public photoViewPagerAdapter(List<photo> mlistphoto) {
        this.mlistphoto = mlistphoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgphoto = view.findViewById(R.id.img_photo);
        photo photo = mlistphoto.get(position);
        imgphoto.setImageResource(photo.getResouId());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if(mlistphoto != null){
            return mlistphoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }
}
