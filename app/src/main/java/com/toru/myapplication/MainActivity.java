package com.toru.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
     private TabLayout tabLayout;
     private FloatingActionButton fab;
     private ViewPager pview;
     private  viewPageAdapter adapter;

    private final static int REQUEST_CODE_PERMISSION_SEND_SMS = 123;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private final static int REQUEST_CODE_PERMISSION_READ_SMS = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        fab = findViewById(R.id.floatingActionButton2);
        adapter = new viewPageAdapter(getSupportFragmentManager());

        // add Fregmanent

        adapter.addFregmet(new fcous(), "fcous");
        adapter.addFregmet(new others(), "others");
        pview = findViewById(R.id.viewpager);
        pview.setAdapter(adapter);
        ;
        tabLayout.setupWithViewPager(pview);


        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }

        else {

        if (checkPermission(Manifest.permission.READ_SMS)) {
            pview.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    // pview.setCurrentItem(tab.getPosition());

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    (Manifest.permission.READ_SMS)}, REQUEST_CODE_PERMISSION_READ_SMS);
        }


        if (checkPermission(Manifest.permission.SEND_SMS)) {

            //we need to use floting button


        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    (Manifest.permission.SEND_SMS)}, REQUEST_CODE_PERMISSION_SEND_SMS);
        }

    }






    }


    private boolean checkPermission(String permission){
        int checkPermission = ContextCompat.checkSelfPermission(this, permission);
        return checkPermission == PackageManager.PERMISSION_GRANTED;
    }
}
