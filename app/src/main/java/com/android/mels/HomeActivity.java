package com.android.mels;



import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.ceritamahasiswaa.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView nMainNav;
    private FrameLayout nMainFrame;

    private HomeFragment homeFragment;
    private DailyFragment dailyFragment;
    private GalleryFragment galleryFragment;
    private MusicVideoFragment musicVideoFragment;
    private ProfileFragment profileFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        nMainFrame = (FrameLayout)findViewById(R.id.main_frame);
        nMainNav = (BottomNavigationView)findViewById(R.id.main_nav);

        homeFragment = new HomeFragment();
        dailyFragment = new DailyFragment();
        galleryFragment = new GalleryFragment();
        musicVideoFragment = new MusicVideoFragment();
        profileFragment = new ProfileFragment();




        nMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                switch (menuItem.getItemId()){


                    case R.id.daily_nav :
                        setFragment(dailyFragment);
                        return true;

                    case R.id.gallery_nav :
                        setFragment(galleryFragment);
                        return true;

                    case R.id.music_nav :
                        setFragment(musicVideoFragment);
                        return true;
//
//                    case R.id.profile_nav :
//                        setFragment(profileFragment);
//                        return true;

                        default:
                            return false;

                }

            }

            private void setFragment(Fragment fragment) {

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();
            }
        });
    }


}
