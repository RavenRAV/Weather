package com.example.raven51.ui.onboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.raven51.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;

public class OnBoardAdapter extends PagerAdapter {

    private ArrayList<onBoardItem>data = new ArrayList<>();


    @Override
    public int getCount() {
        return data.size();
    }

    public void update(ArrayList<onBoardItem> data){
        this.data =data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        TextView textView;
        ImageView imageView;

        LayoutInflater inflater = (LayoutInflater)container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.item_onboard, container, false);

        textView = itemView.findViewById(R.id.tvTitle);
        imageView = itemView.findViewById(R.id.imageOnBoard);

        textView.setText(data.get(position).getTitle());
        imageView.setImageResource(data.get(position).getImage());
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       // container.removeView((LinearLayout)object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


}
