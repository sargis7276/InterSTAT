package com.example.interstat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Vector;


class Player{

    Player(int number, String name, int gamesPlayed, int minutesPlayed ,int goals, int assists, int yellows, int reds){

        this.number = number;
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.minutesPlayed = minutesPlayed;
        this.goals = goals;
        this.yellows =yellows;
        this.reds = reds;

        this.imagename = "";

    }

    int number;
    String name;
    int gamesPlayed;
    int minutesPlayed;
    int goals;
    int assists;
    int yellows;
    int reds;

    ImageView image;
    String imagename;
    int res;


}



public class squadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView player1, player2, player3, player4, player5, player6, player7, player8, player9, player10, player11, player12, player13, player14;
    private TextView player15, player16,player17,player18,player19,player20,player21,player22,player24,player25,player26,player27;
    private Spinner spinner1;
    private  TextView posGk, posLCB, posCB, posRCB, posLWB, posLCM, posCM, posRCM, posRWD, posLS, posFW1, posCF, posFW2, posRS;
    private TextView goalTextView, assistTextView, redTextView, yellowTextView;
    private static final String[] formations={"Formation","3-5-2","3-4-3"};
    private TextView lineupTextView;
    private Button subButton;
    private Button startButton;
    private Button endButton;
    private Boolean subFlag = false;
    private ScrollView playerScrollView;
    private TextView imaginaryTexxtView;
    private TextView ourgoalsTV, theirGoalsTV;
    int goalsScored, goalsConceded;

    Vector<TextView> playersVector = new Vector<>();
    Vector<TextView> selectedLineup = new Vector<>();
    Vector<TextView> subsLineup = new Vector<>();
    Vector<TextView> positionsVector = new Vector<>();
    Vector<Player> playersOBJVec = new Vector();

    private void setPlayers(){
        Player Handanovic = new Player(1, "Samir Handanovic", 0, 0, 0, 0, 0, 0); playersOBJVec.add(Handanovic);
        Player Padelli = new Player(27, "Daniele Padelli", 0, 0, 0, 0, 0, 0); playersOBJVec.add(Padelli);
        Player Berni = new Player(46, "Tommaso Berni", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Berni);
        Player Godin = new Player(2, "Diego Godin", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Godin);
        Player Vrij = new Player(6, "Stefan de Vrij", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Vrij);
        Player Dimarco = new Player(21, "Federico Dimarco", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Dimarco);
        Player Ranocchia = new Player(13, "Andrea Ranocchia", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Ranocchia);
        Player Bastoni = new Player(95, "Alessandro Bastoni", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Bastoni);
        Player Pirola = new Player(31, "Lorenzo Pirola", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Pirola);
        Player dAmbrosio = new Player(33, "Danilo D'Ambrosio", 0, 0, 0, 0, 0, 0);playersOBJVec.add(dAmbrosio);
        Player Skriniar = new Player(37, "Milan Skriniar", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Skriniar);
        Player Biraghi = new Player(34, "Cristiano Biraghi", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Biraghi);
        Player Gagliardini = new Player(5, "Roberto Gagliardini", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Gagliardini);
        Player Vecino = new Player(8, "Matias Vecino", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Vecino);
        Player Sensi = new Player(12, "Stefano Sensi", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Sensi);
        Player Agoume = new Player(32, "Lucien Agoume", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Agoume);
        Player Asamoa = new Player(18, "Kwadwo Asamoa", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Asamoa);
        Player Lazaro = new Player(19, "Valentino Lazaro", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Lazaro);
        Player Valero = new Player(20, "Borja Valero", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Valero);
        Player Barella = new Player(23, "Nicolo Barella", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Barella);
        Player Brozovic = new Player(77, "Marcelo Brozovic", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Brozovic);
        Player Sanchez = new Player(7, "Alexis Sanchez", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Sanchez);
        Player Esposito = new Player(30, "Sebastiano Esposito", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Esposito);
        Player Lukaku = new Player(9, "Romelu Lukaku", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Lukaku);
        Player Martínez = new Player(10, "Lautaro Martínez", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Martínez);
        Player Politano = new Player(16, "Matteo Politano", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Politano);
        Player Candreva = new Player(87, "Antnio Candreva", 0, 0, 0, 0, 0, 0);playersOBJVec.add(Candreva);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener1
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    Intent intent = new Intent(squadActivity.this, MainActivity.class);
                    startActivity(intent);

                    break;
                case R.id.navigation_dashboard:



                    break;

                case R.id.navigation_notifications:
                    Intent myintenttable = new Intent(squadActivity.this, table.class);
                    startActivity(myintenttable);
                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_squad);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        BottomNavigationView navView = findViewById(R.id.nav_view1);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener1);
        Menu menu = navView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        setPlayers();

        playerScrollView = findViewById(R.id.scrollView2);
        goalTextView = findViewById(R.id.textViewgoal);
        assistTextView = findViewById(R.id.textViewassist);
        yellowTextView = findViewById(R.id.textViewyellow);
        redTextView = findViewById(R.id.textViewred);

        goalTextView.setVisibility(View.INVISIBLE);
        yellowTextView.setVisibility(View.INVISIBLE);
        redTextView.setVisibility(View.INVISIBLE);
        assistTextView.setVisibility(View.INVISIBLE);
        imaginaryTexxtView = findViewById(R.id.ImaginarytextView);
        ourgoalsTV = findViewById(R.id.ourgoalstextView);
        theirGoalsTV = findViewById(R.id.theirgoalsTextView);
        goalsScored = 0;
        goalsConceded = 0;


        //shows the lineup on the buttom of the field
        //could be used later for the DB
        lineupTextView = findViewById(R.id.lineupTextView);
        startButton = findViewById(R.id.startButton);
        subButton = findViewById(R.id.subButton);
        subButton.setVisibility(View.INVISIBLE);
        endButton = findViewById(R.id.buttonEnd);



        //get the positions and add them to the vactor
        posGk = findViewById(R.id.textViewGK);
        positionsVector.add(posGk);
        posLCB = findViewById(R.id.textViewLCB);
        positionsVector.add(posLCB);
        posCB = findViewById(R.id.textViewCB);
        positionsVector.add(posCB);
        posRCB = findViewById(R.id.textViewRCB);
        positionsVector.add(posRCB);

        posLWB = findViewById(R.id.textViewLWB);
        positionsVector.add(posLWB);
        posLCM = findViewById(R.id.textViewLCM);
        positionsVector.add(posLCM);
        posCM = findViewById(R.id.textViewCM);
        positionsVector.add(posCM);
        posRCM = findViewById(R.id.textViewRCM);
        positionsVector.add(posRCM);
        posRWD = findViewById(R.id.textViewRWD);
        positionsVector.add(posRWD);

        posLS = findViewById(R.id.textViewLS);
        positionsVector.add(posLS);
        posFW1 = findViewById(R.id.textViewFW1);
        positionsVector.add(posFW1);
        posCF = findViewById(R.id.textViewCF);
        positionsVector.add(posCF);
        posFW2 = findViewById(R.id.textViewFW2);
        positionsVector.add(posFW2);
        posRS = findViewById(R.id.textViewRS);
        positionsVector.add(posRS);



        //get the text view with player names
        player1 = findViewById(R.id.textViewHanda);
        player2 = findViewById(R.id.textViewPadelli);
        player3 = findViewById(R.id.textViewBerni);
        player4 = findViewById(R.id.textViewGodin);
        player5 = findViewById(R.id.textViewVrij);
        player6 = findViewById(R.id.textViewRanok);
        player7 = findViewById(R.id.textViewBastoni);
        player8 = findViewById(R.id.textViewPirola);
        player9 = findViewById(R.id.textViewDambro);
        player10 = findViewById(R.id.textViewSkri);
        player11 = findViewById(R.id.textViewBirag);
        player12 = findViewById(R.id.textViewGaglia);
        player13 = findViewById(R.id.textViewVecino);
        player14 = findViewById(R.id.textViewSensi);
        player15 = findViewById(R.id.textViewAgume);
        player16 = findViewById(R.id.textViewAsa);
        player17 = findViewById(R.id.textViewLazaro);
        player18 = findViewById(R.id.textViewValero);
        player19 = findViewById(R.id.textViewBarella);
        player20 = findViewById(R.id.textViewBrozo);
        player21 = findViewById(R.id.textViewCandreva);
        player22 = findViewById(R.id.textViewSanchez);
        player24 = findViewById(R.id.textViewRom);
        player25 = findViewById(R.id.textViewLautaro);
        player26 = findViewById(R.id.textViewPolitano);
        player27 = findViewById(R.id.textViewEspos);

        //put the players in a vector to make settings easier

        playersVector.add(player1);
        playersVector.add(player2);
        playersVector.add(player3);
        playersVector.add(player4);
        playersVector.add(player5);
        playersVector.add(player6);
        playersVector.add(player7);
        playersVector.add(player8);
        playersVector.add(player9);
        playersVector.add(player10);
        playersVector.add(player11);
        playersVector.add(player12);
        playersVector.add(player13);
        playersVector.add(player14);
        playersVector.add(player15);
        playersVector.add(player16);
        playersVector.add(player17);
        playersVector.add(player18);
        playersVector.add(player19);
        playersVector.add(player20);
        playersVector.add(player21);
        playersVector.add(player22);
        playersVector.add(player24);
        playersVector.add(player25);
        playersVector.add(player26);
        playersVector.add(player27);



        spinner1 = findViewById(R.id.spinner2);
        ArrayAdapter<String>adapter = new ArrayAdapter<>(squadActivity.this, android.R.layout.simple_spinner_item, formations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);



        try {

            //create a database or open if it already exist with setting name mode and special error catching method
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("players", MODE_PRIVATE, null);

            //create table and insert data
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS seriea (number INTEGER(2) PRIMARY KEY, name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2))");
   //         myDatabase.execSQL("CREATE TABLE IF NOT EXISTS ucl (number INTEGER(2), name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2), number INTEGER PRIMARY KEY)");
   //         myDatabase.execSQL("CREATE TABLE IF NOT EXISTS el (number INTEGER(2), name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2), number INTEGER PRIMARY KEY)");
   //         myDatabase.execSQL("CREATE TABLE IF NOT EXISTS coppa (number INTEGER(2), name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2), number INTEGER PRIMARY KEY)");
   //         myDatabase.execSQL("CREATE TABLE IF NOT EXISTS total (number INTEGER(2), name VARCHAR, games INTEGER(3), minutes INTEGER(4),goals INTEGER(2), assists INTEGER(2), yellows INTEGER(2), reds INTEGER(2), number INTEGER PRIMARY KEY)");

            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (1, 'Samir Handanovic', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (27, 'Daniele Padelli', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (46, 'Tommaso Berni', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (2, 'Diego Godin', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (6, 'Stefan de Vrij', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (21, 'Federico Dimarco', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (13, 'Andrea Ranocchia', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (95, 'Alessandro Bastoni', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (31, 'Lorenzo Pirola', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (33, 'Danilo DAmbrosio', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (37, 'Milan Skriniar', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (34, 'Cristiano Biraghi', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (5, 'Roberto Gagliardini', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (8, 'Matias Vecino', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (12, 'Stefano Sensi', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (32, 'Lucien Agoume', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (18, 'Kwadwo Asamoa', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (19, 'Valentino Lazaro', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (20, 'Borja Valero', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (23, 'Nicolo Barella', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (77, 'Marcelo Brozovic', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (87, 'Antnio Candreva', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (7, 'Alexis Sanchez', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (30, 'Sebastiano Esposito', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (9, 'Romelu Lukaku', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (10, 'Lautaro Martínez', 0, 0, 0, 0, 0, 0)");
            myDatabase.execSQL("INSERT OR IGNORE INTO seriea (number, name, games, minutes, goals, assists, yellows, reds) VALUES (16, 'Matteo Politano', 0, 0, 0, 0, 0, 0)");


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                posGk.setVisibility(View.INVISIBLE);
                posLCB.setVisibility(View.INVISIBLE);
                posCB.setVisibility(View.INVISIBLE);
                posRCB.setVisibility(View.INVISIBLE);

                posLWB.setVisibility(View.INVISIBLE);
                posLCM.setVisibility(View.INVISIBLE);
                posCM.setVisibility(View.INVISIBLE);
                posRCM.setVisibility(View.INVISIBLE);
                posRWD.setVisibility(View.INVISIBLE);

                posLS.setVisibility(View.INVISIBLE);
                posFW1.setVisibility(View.INVISIBLE);
                posCF.setVisibility(View.INVISIBLE);
                posFW2.setVisibility(View.INVISIBLE);
                posRS.setVisibility(View.INVISIBLE);

                break;
            case 1:
                setOnTouchListeners();
                setOnDragListerners();
                posGk.setVisibility(View.VISIBLE);
                posLCB.setVisibility(View.VISIBLE);
                posCB.setVisibility(View.VISIBLE);
                posRCB.setVisibility(View.VISIBLE);

                posLWB.setVisibility(View.VISIBLE);
                posLCM.setVisibility(View.VISIBLE);
                posCM.setVisibility(View.VISIBLE);
                posRCM.setVisibility(View.VISIBLE);
                posRWD.setVisibility(View.VISIBLE);

                posFW1.setVisibility(View.VISIBLE);
                posFW2.setVisibility(View.VISIBLE);
                posLS.setVisibility(View.INVISIBLE);
                posCF.setVisibility(View.INVISIBLE);
                posRS.setVisibility(View.INVISIBLE);

                break;
            case 2:
                setOnTouchListeners();
                setOnDragListerners();
                // Whatever you want to happen when the thrid item gets selected
                posGk.setVisibility(View.VISIBLE);
                posLCB.setVisibility(View.VISIBLE);
                posCB.setVisibility(View.VISIBLE);
                posRCB.setVisibility(View.VISIBLE);

                posLWB.setVisibility(View.VISIBLE);
                posLCM.setVisibility(View.VISIBLE);
                posCM.setVisibility(View.INVISIBLE);
                posRCM.setVisibility(View.VISIBLE);
                posRWD.setVisibility(View.VISIBLE);

                posLS.setVisibility(View.VISIBLE);
                posCF.setVisibility(View.VISIBLE);
                posRS.setVisibility(View.VISIBLE);
                posFW1.setVisibility(View.INVISIBLE);
                posFW2.setVisibility(View.INVISIBLE);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @SuppressLint("ClickableViewAccessibility")
    public void setOnTouchListeners() {

        //set touch listeners
        player1.setOnTouchListener(new ChoiceTouchListener());
        player2.setOnTouchListener(new ChoiceTouchListener());
        player3.setOnTouchListener(new ChoiceTouchListener());
        player4.setOnTouchListener(new ChoiceTouchListener());
        player5.setOnTouchListener(new ChoiceTouchListener());
        player6.setOnTouchListener(new ChoiceTouchListener());
        player7.setOnTouchListener(new ChoiceTouchListener());
        player8.setOnTouchListener(new ChoiceTouchListener());
        player9.setOnTouchListener(new ChoiceTouchListener());
        player10.setOnTouchListener(new ChoiceTouchListener());
        player11.setOnTouchListener(new ChoiceTouchListener());
        player12.setOnTouchListener(new ChoiceTouchListener());
        player13.setOnTouchListener(new ChoiceTouchListener());
        player14.setOnTouchListener(new ChoiceTouchListener());
        player15.setOnTouchListener(new ChoiceTouchListener());
        player16.setOnTouchListener(new ChoiceTouchListener());
        player17.setOnTouchListener(new ChoiceTouchListener());
        player18.setOnTouchListener(new ChoiceTouchListener());
        player19.setOnTouchListener(new ChoiceTouchListener());
        player20.setOnTouchListener(new ChoiceTouchListener());
        player21.setOnTouchListener(new ChoiceTouchListener());
        player22.setOnTouchListener(new ChoiceTouchListener());
        player24.setOnTouchListener(new ChoiceTouchListener());
        player25.setOnTouchListener(new ChoiceTouchListener());
        player26.setOnTouchListener(new ChoiceTouchListener());
        player27.setOnTouchListener(new ChoiceTouchListener());

        assistTextView.setOnTouchListener(new ChoiceTouchListener());
        goalTextView.setOnTouchListener(new ChoiceTouchListener());
        yellowTextView.setOnTouchListener(new ChoiceTouchListener());
        redTextView.setOnTouchListener(new ChoiceTouchListener());


    }

    public void setOnDragListerners() {
        //get the positions
        posGk.setOnDragListener(new ChoiceDragListener());
        posLCB.setOnDragListener(new ChoiceDragListener());
        posCB.setOnDragListener(new ChoiceDragListener());
        posRCB.setOnDragListener(new ChoiceDragListener());
        posLWB.setOnDragListener(new ChoiceDragListener());
        posLCM.setOnDragListener(new ChoiceDragListener());
        posCM.setOnDragListener(new ChoiceDragListener());
        posRCM.setOnDragListener(new ChoiceDragListener());
        posRWD.setOnDragListener(new ChoiceDragListener());
        posLS.setOnDragListener(new ChoiceDragListener());
        posFW1.setOnDragListener(new ChoiceDragListener());
        posCF.setOnDragListener(new ChoiceDragListener());
        posFW2.setOnDragListener(new ChoiceDragListener());
        posRS.setOnDragListener(new ChoiceDragListener());
    }

    @SuppressLint("SetTextI18n")
    public void resetButtonPress(View view){
        subFlag = false;
        startButton.setClickable(true);
        subButton.setVisibility(View.INVISIBLE);

        goalTextView.setVisibility(View.INVISIBLE);
        yellowTextView.setVisibility(View.INVISIBLE);
        redTextView.setVisibility(View.INVISIBLE);
        assistTextView.setVisibility(View.INVISIBLE);
        ourgoalsTV.setVisibility(View.INVISIBLE);
        theirGoalsTV.setVisibility(View.INVISIBLE);

        for(TextView i : positionsVector) {
            i.setVisibility(View.INVISIBLE);
            i.setTypeface(Typeface.DEFAULT);
            i.setBackgroundResource(R.drawable.dottv1);
            i.setTextColor(Color.WHITE);
            i.setGravity(Gravity.CENTER);
            i.setTextSize(24);
        }
        //enable players after reset
        for(TextView i : playersVector){
            i.setEnabled(true);
       }


        posGk.setText("GK");
        posLCB.setText("LCB");
        posCB.setText("CB");
        posRCB.setText("RCB");
        posLWB.setText("LWB");
        posLCM.setText("LCM");
        posCM.setText("CM");
        posRCM.setText("RCM");
        posRWD.setText("RWD");
        posLS.setText("LS");
        posFW1.setText("FW1");
        posCF.setText("CF");
        posFW2.setText("FW2");
        posRS.setText("RS");

        spinner1.setSelection(0);
        spinner1.setEnabled(true);
        startButton.setClickable(true);
        lineupTextView.setText("");
        selectedLineup.clear();

        for(TextView i : playersVector) {
            i.setVisibility(View.VISIBLE);
        }

        playerScrollView.setVisibility(View.VISIBLE);
        setOnTouchListeners();
        setOnDragListerners();
    }


    public void startButtonPress(View view) {

        subButton.setVisibility(View.VISIBLE);
        startButton.setClickable(false);

        goalTextView.setVisibility(View.VISIBLE);
        yellowTextView.setVisibility(View.VISIBLE);
        redTextView.setVisibility(View.VISIBLE);
        assistTextView.setVisibility(View.VISIBLE);

        if(selectedLineup.size() < 11)
        {
            startButton.setClickable(true);
            subButton.setVisibility(View.INVISIBLE);

            goalTextView.setVisibility(View.INVISIBLE);
            yellowTextView.setVisibility(View.INVISIBLE);
            redTextView.setVisibility(View.INVISIBLE);
            assistTextView.setVisibility(View.INVISIBLE);

            Toast.makeText(squadActivity.this, "Enter full lineup first", Toast.LENGTH_LONG).show();
        }
        else
        {
            spinner1.setEnabled(false);
            for(TextView i : selectedLineup) {
                lineupTextView.append(i.getText());
                lineupTextView.append(", ");
            }
            //disable the player list until sub button is pressed
            for(TextView i : playersVector){
                i.setEnabled(false);
            }

        }

    }

    public void subButtonPressed(View view){

        subFlag = true;

        //enable players list to allow a substitution
        //also disable them again after a sub is made
        for(TextView i : playersVector){
            i.setEnabled(true);
        }

        // I don't need this anymore bc I don't stop them anymore
        //should I??
        //setOnDragListerners();

    }

    public void endButtonPressed(View view){

        // vars to be use to tie all up for an sql statement
        String number;
        String name;
        int games;
        int minutes;
        int goals;
        int assists;
        int yellows;
        int reds;

        String sql;

        try {
            //create a database or open if it already exist with setting name mode and special error catching method
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("players", MODE_PRIVATE, null);

            //myDatabase.execSQL("UPDATE seriea SET goals = goals+1 WHERE number = 9");

            //now go over the players vector and add theur data to the DB using strings !

            for(int i = 0; i < playersOBJVec.size(); i++) {
                //putting all the data in strings
                number = Integer.toString(playersOBJVec.get(i).number);
                name = playersOBJVec.get(i).name;
                games = playersOBJVec.get(i).gamesPlayed;
                minutes = playersOBJVec.get(i).minutesPlayed;
                goals = playersOBJVec.get(i).goals;
                assists = playersOBJVec.get(i).assists;
                yellows = playersOBJVec.get(i).yellows;
                reds = playersOBJVec.get(i).reds;

                //for later Optiziation
                //String qw;
                //qw = "SELECT * FROM seriea WHERE number = " + number;
                //Cursor rCursor = myDatabase.rawQuery(qw, null);


                sql = "UPDATE seriea SET games = games + " + games + " WHERE number = " + number;
                myDatabase.execSQL(sql);
                sql = "UPDATE seriea SET minutes = minutes + " + minutes + " WHERE number = " + number;
                myDatabase.execSQL(sql);
                sql = "UPDATE seriea SET goals = goals + " + goals + " WHERE number = " + number;
                myDatabase.execSQL(sql);
                sql = "UPDATE seriea SET assists = assists + " + assists + " WHERE number = " + number;
                myDatabase.execSQL(sql);
                sql = "UPDATE seriea SET yellows = yellows + " + yellows + " WHERE number = " + number;
                myDatabase.execSQL(sql);
                sql = "UPDATE seriea SET reds = reds + " + reds + " WHERE number = " + number;
                myDatabase.execSQL(sql);
            }

            //String sql = "SELECT * FROM seriea WHERE number = " + number;
            //row curser
            // Cursor rCursor = myDatabase.rawQuery(sql, null);
            // rCursor.moveToFirst();

            // show score
            resetButtonPress(view);
            ourgoalsTV.setText(String.valueOf(goalsScored));
            theirGoalsTV.setText(String.valueOf(goalsConceded));
            ourgoalsTV.setVisibility(View.VISIBLE);
            theirGoalsTV.setVisibility(View.VISIBLE);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void showAddItemDialog() {
        final EditText taskEditText = new EditText(this);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Substitution minute")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String minute = String.valueOf(taskEditText.getText());
                        lineupTextView.append(minute);
                        lineupTextView.append("'");
                        imaginaryTexxtView.setText(minute);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    /**
     * ChoiceTouchListener will handle touch events on draggable views
     *
     */
    private final class ChoiceTouchListener implements View.OnTouchListener {
        @SuppressLint({"NewApi", "ClickableViewAccessibility"})
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                /*
                 * Drag details: we only need default behavior
                 * - clip data could be set to pass data as part of drag
                 * - shadow can be tailored
                 */
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * DragListener will handle dragged views being dropped on the drop area
     * - only the drop action will have processing added to it as we are not
     * - amending the default behavior for other parts of the drag process
     *
     */
    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    // show possible drop areas depending on the formation chosen
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    /* no action necessary */
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;

                    if(dropped.getId() == goalTextView.getId())
                    {

                        //find the player that is selected and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {
                            if(dropTarget.getText().toString().equals(playersOBJVec.get(i).name)){
                                playersOBJVec.get(i).goals += 1;
                                if(playersOBJVec.get(i).number == 1)
                                    goalsConceded++;
                                else
                                    goalsScored++;
                            }
                        }

                        lineupTextView.append("\n");
                        lineupTextView.append(dropTarget.getText());
                        lineupTextView.append(" --> ");
                        lineupTextView.append("Goal ");
                        break;
                    }
                    else if (dropped.getId() == assistTextView.getId())
                    {
                        //find the player that is selected and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {
                            if(dropTarget.getText().toString().equals(playersOBJVec.get(i).name)){
                                playersOBJVec.get(i).assists += 1;
                            }
                        }
                        lineupTextView.append("\n");
                        lineupTextView.append(dropTarget.getText());
                        lineupTextView.append(" --> ");
                        lineupTextView.append("Assist ");
                        break;
                    }
                    else if (dropped.getId() == yellowTextView.getId())
                    {
                        //find the player that is selected and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {
                            if(dropTarget.getText().toString().equals(playersOBJVec.get(i).name)){
                                playersOBJVec.get(i).yellows += 1;
                            }
                        }
                        lineupTextView.append("\n");
                        lineupTextView.append(dropTarget.getText());
                        lineupTextView.append(" --> ");
                        lineupTextView.append("Yellow card");
                        break;
                    }
                    else if (dropped.getId() == redTextView.getId())
                    {
                        //find the player that is selected and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {
                            if(dropTarget.getText().toString().equals(playersOBJVec.get(i).name)){
                                playersOBJVec.get(i).reds += 1;
                            }
                        }
                        lineupTextView.append("\n");
                        lineupTextView.append(dropTarget.getText());
                        lineupTextView.append(" --> ");
                        lineupTextView.append("Red card");
                        dropTarget.setVisibility(View.GONE);
                        break;
                    }


                    if(!subFlag)
                    {
                        selectedLineup.add(dropped);
                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.GONE);


                        //find the player that is selected and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {

                            if(dropped.getText().toString().equals(playersOBJVec.get(i).name)){
                                dropTarget.setText(playersOBJVec.get(i).name);
                                playersOBJVec.get(i).gamesPlayed += 1;
                                playersOBJVec.get(i).minutesPlayed += 90;

                            }
                        }

                        //update the text in the target view to reflect the data being dropped
                      //  dropTarget.setText(dropped.getText().toString());
                        //dropTarget.setBackground(null);


                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTextColor(Color.WHITE);
                        dropTarget.setTextSize(17);
                        dropTarget.setBackgroundResource(R.drawable.bklp);
                        dropTarget.setGravity(0);

                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done


                    }
                    else
                    {
                      //  showAddItemDialog();
                        subsLineup.add(dropped);
                        lineupTextView.append("\nSubstitution: ");
                        lineupTextView.append(dropTarget.getText());
                        lineupTextView.append("(Out), <-- ");
                        lineupTextView.append(subsLineup.lastElement().getText());
                        lineupTextView.append("(In) ");

                        //disabling the players list
                        //for(TextView i : playersVector){
                        //    i.setEnabled(false);
                        //}
                        playerScrollView.setEnabled(false);
                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.GONE);

                        Player incoming = null;
                        Player outgoing = null;
                        //find the player that is selected and the one that is getting subbed and update the stats
                        for(int i = 0; i < playersOBJVec.size(); i++) {

                            //find the one coming on
                            if(dropped.getText().toString().equals(playersOBJVec.get(i).name)){
                                incoming = playersOBJVec.get(i);
                                //dropTarget.setText(playersOBJVec.get(i).name);
                                //playersOBJVec.get(i).gamesPlayed += 1;
                                //playersOBJVec.get(i).minutesPlayed = playersOBJVec.get(i).minutesPlayed + (15);
                            }
                            //found the player going out
                            else if(dropTarget.getText().toString().equals(playersOBJVec.get(i).name)){
                                outgoing = playersOBJVec.get(i);
                                //dropTarget.setText(playersOBJVec.get(i).name);
                                //playersOBJVec.get(i).minutesPlayed = playersOBJVec.get(i).minutesPlayed - (75);
                            }
                        }
                        //update the text view
                        dropTarget.setText(incoming.name);

                        //update player stats for incoming player
                        incoming.gamesPlayed += 1;
                        incoming.minutesPlayed = incoming.minutesPlayed + (15);

                        //update player stats for outgoing player
                        outgoing.minutesPlayed = outgoing.minutesPlayed - (15);

                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTextColor(Color.WHITE);
                        dropTarget.setTextSize(17);
                        dropTarget.setBackgroundResource(R.drawable.bklp);
                        dropTarget.setGravity(0);

                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        //disable list
                        for(TextView i : playersVector){
                            i.setEnabled(false);
                        }

                    }

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }

}
