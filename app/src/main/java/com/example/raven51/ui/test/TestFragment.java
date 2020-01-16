package com.example.raven51.ui.test;

import android.widget.TextView;

import com.example.raven51.R;
import com.example.raven51.ui.base.BaseFragment;

import butterknife.OnClick;

public class TestFragment extends BaseFragment {

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }
    @OnClick(R.id.day)
    public void textClicked(TextView textView){
        textView.setText("какие-то слова");
    }
}
