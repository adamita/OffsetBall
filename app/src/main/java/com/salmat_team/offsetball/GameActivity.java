package com.salmat_team.offsetball;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by adamita on 2016. 03. 20..
 */
public class GameActivity extends AppCompatActivity {

    String fragm="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);
        fragm = getIntent().getExtras().getString("fragment",null);

        //TextView TV=(TextView)findViewById(R.id.textView);
        //TV.setText(fragm);



        }


    private void loadFragment(){
        FragmentManager fm =getSupportFragmentManager();
        FragmentTransaction ft =fm.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

        switch (fragm) {
            case "Game":
                ft.addToBackStack(GameFragment.class.getName());
                ft.replace(R.id.fragment_game1, new GameFragment());
                break;
            case "Options":
                ft.addToBackStack(OptionsFragment.class.getName());
                ft.replace(R.id.fragment_options, new OptionsFragment());
                break;
        }

        ft.commit();
    }



    }
