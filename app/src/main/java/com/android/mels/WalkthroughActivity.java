package com.android.mels;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.ceritamahasiswaa.R;

public class WalkthroughActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout dotLayout;
    private TextView[]dotTxt;
    private int[]layouts;
    private Button btnNext;
    private Button btnSkip;
    private PagerAdapterActivity pagerAdapterActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);


        setStatusBarTransparent();

        viewPager = findViewById(R.id.vPage);
        dotLayout = findViewById(R.id.dot_layout);
        btnNext = findViewById(R.id.btn_next);
        btnSkip = findViewById(R.id.btn_skip);

        //When this button click, jump to Home
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startHomeActivity();

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int currentPage = viewPager.getCurrentItem()+1;
                if(currentPage < layouts.length){
                    //move to the next page
                    viewPager.setCurrentItem(currentPage);
                } else {
                    startHomeActivity();
                }

            }
        });
        layouts = new int[]{R.layout.activity_slide,R.layout.activity_slide2,R.layout.activity_slide3};
        pagerAdapterActivity = new PagerAdapterActivity(layouts,getApplicationContext());
        viewPager.setAdapter(pagerAdapterActivity);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == layouts.length-1){
                    //Last page
                    btnNext.setText("START");
                    btnSkip.setVisibility(View.GONE);
                } else {
                    btnNext.setText("NEXT");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        setDotStatus(0);
    }

    private void setDotStatus(int page){
        dotLayout.removeAllViews();
        dotTxt = new TextView[layouts.length];
        for (int i = 0; i <dotTxt.length; i++) {
            dotTxt[i] = new TextView(this);
            dotTxt[i].setText(Html.fromHtml("&#8226;"));
            dotTxt[i].setTextSize(30);
            dotTxt[i].setTextColor(Color.parseColor("#ffffff"));
            dotLayout.addView(dotTxt[i]);
        }

        if(dotTxt.length>0){
            dotTxt[page].setTextColor(Color.parseColor("#c0c0c0"));
        }
    }
    private void startHomeActivity(){
        startActivity(new Intent(WalkthroughActivity.this, HomeActivity.class));
        finish();
    }
    private void setStatusBarTransparent(){
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
