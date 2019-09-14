package com.example.interstat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Home page
 * Still not sure what to do here
 * League standings (Kinda hard)
 * Inter Schedule (get the score after a game from squad activity)
 *
 * */

public class MainActivity extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    break;
                case R.id.navigation_dashboard:


                    Intent myintent = new Intent(MainActivity.this, squadActivity.class);
                    startActivity(myintent);
                    break;

                case R.id.navigation_notifications:
                    Intent myintenttable = new Intent(MainActivity.this, table.class);
                    startActivity(myintenttable);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
    }

}
