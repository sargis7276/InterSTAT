package com.example.interstat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class table extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener1
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent = new Intent(table.this, MainActivity.class);
                    startActivity(intent);

                    break;
                case R.id.navigation_dashboard:

                    Intent myintent = new Intent(table.this, squadActivity.class);
                    startActivity(myintent);

                    break;

                case R.id.navigation_notifications:

                    break;
            }
            return false;
        }
    };

    SQLiteDatabase myDatabase;
    TableLayout table_layout;
    Button clearButton;
    Button lockButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BottomNavigationView navView = findViewById(R.id.nav_view3);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener1);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        table_layout = findViewById(R.id.table_layout);
        clearButton = findViewById(R.id.buttonClearDB);
        lockButton = findViewById(R.id.buttonLock);


        clearButton.setClickable(false);
        clearButton.setAlpha(0.5f);

        updateDB();
    }

    //updates table from DB
    public void updateDB() {
        try {
            myDatabase = this.openOrCreateDatabase("players", MODE_PRIVATE, null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS seriea (number INTEGER(2) PRIMARY KEY, name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2))");


            //loop for rows
            for (int i = 1; i < 28; i++) {
                //select a raw
                TableRow row = (TableRow) table_layout.getChildAt(i);

                //loop for column
                for (int j = 1; j < 8; j++) {
                    TextView sample = (TextView) row.getChildAt(j);

                    //get the data of 0th child of the row (first column)
                    TextView numberView = (TextView) row.getChildAt(0);
                    String number = numberView.getText().toString();

                    String sql = "SELECT * FROM seriea WHERE number = " + number;
                    //row curser
                    Cursor rCursor = myDatabase.rawQuery(sql, null);
                    rCursor.moveToFirst();
                    String text = rCursor.getString(j);
                    sample.setText(text);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void lock(View view) {
        clearButton.setClickable(true);
        clearButton.setAlpha(1);
        lockButton.setBackgroundResource(R.drawable.unlocked);
    }
    public void clearDB(View view){

        try {
            myDatabase = this.openOrCreateDatabase("players", MODE_PRIVATE, null);

            myDatabase.execSQL("DROP TABLE " + "seriea");


            //loop for rows
            for (int i = 1; i < 28; i++) {
                //select a raw
                TableRow row = (TableRow) table_layout.getChildAt(i);

                //loop for column
                for (int j = 2; j < 8; j++) {
                    TextView sample = (TextView) row.getChildAt(j);

                    //get the data of 0th child of the row (first column)
                    sample.setText("0");
                }
            }
            /**
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS seriea (number INTEGER(2) PRIMARY KEY, name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2))");


            //get data out of the database
            //using a cursor object
            //cursor allows us to loop through all the results of a querry and do something with them
            Cursor c = myDatabase.rawQuery("SELECT * FROM users", null);

            //get column indexes for name and age
            int numberIndex = c.getColumnIndex("number");
            int nameIndex = c.getColumnIndex("name");
            int gamesIndex = c.getColumnIndex("games");
            int minutesIndec = c.getColumnIndex("minutes");
            int goalsIndex = c.getColumnIndex("goals");
            int assistsIndex = c.getColumnIndex("assists");
            int yellowsIndex = c.getColumnIndex("yellows");
            int redsIndec = c.getColumnIndex("reds");

            c.moveToFirst();

            while (c != null){

                myDatabase.execSQL("UPDATE seriea SET goals = goals+1 WHERE number = 9");

                Log.i("name", c.getString(nameIndex));
                Log.i("age", Integer.toString(c.getInt(ageIndex)));
                Log.i("id", Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }

             */
        }
        catch (Exception e) {
            e.printStackTrace();

        }

    }

}