package com.example.raven51.ui.onboard;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.raven51.R;
import com.example.raven51.ui.base.BaseActivity;
import com.example.raven51.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;


public class OnBoardActivity extends BaseActivity {
//    private List<View> pages;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;


    @Override
    protected int getViewLayout() {
        return R.layout.activity_on_board;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        String[] text = new String[]{"1 page", "2 page", "3 page", "4 page" };
        int[] pict = new int[]{R.drawable.im1, R.drawable.im2,R.drawable.im3 };

        initViewPagerAdapter();

//        pages = new ArrayList<View>();
//        pages.add(View.inflate(R.));
    }

    private ArrayList<onBoardItem> getData(){
        ArrayList<onBoardItem> items = new ArrayList<>();
        items.add(new onBoardItem(getResources().getString(R.string.page1), R.drawable.rain) );
        items.add(new onBoardItem(getResources().getString(R.string.page2), R.drawable.snow) );
        items.add(new onBoardItem(getResources().getString(R.string.page3), R.drawable.cloud) );
        items.add(new onBoardItem(getResources().getString(R.string.page4), R.drawable.im4_2) );

        return items;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_onboard_skip, menu);
        return true;
    }
    public static void start(Context context){
        context.startActivity(new Intent(context, OnBoardActivity.class));
    }

    public void Skip(MenuItem item) {
        MainActivity.start(this);
        finish();
    }

    private void initViewPagerAdapter(){
        OnBoardAdapter adapter = new OnBoardAdapter();
        viewPager.setAdapter(adapter);
        adapter.update(getData());

    }
}


