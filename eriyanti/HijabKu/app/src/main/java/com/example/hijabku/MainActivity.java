package com.example.hijabku;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import static android.net.sip.SipErrorCode.TIME_OUT;

public class MainActivity extends AppCompatActivity {

    public static int TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this, Menu.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }
}
