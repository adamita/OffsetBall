package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    String PrefFileName= "OffsetBall";
    long time=0;
    String name="Player's name :)";
    String BestName="Player's name :)";
    long timeNow;


    private void Save()
    {
        SharedPreferences settings=getSharedPreferences(PrefFileName, 0);
        SharedPreferences.Editor editor =settings.edit();
        editor.putString("BestName", BestName);
        editor.putLong("time",time);
        editor.putString("ActualName",name);
        editor.commit();
    }

    private void Load()
    {
        SharedPreferences settings=getSharedPreferences(PrefFileName, 0);
        BestName=settings.getString("BestName","Player");
        time=settings.getLong("time",0L);
        name=settings.getString("ActualName","Player");
        timeNow=0L;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Load();
        AppCompatButton start = (AppCompatButton) findViewById(R.id.StartButton);
        AppCompatButton options = (AppCompatButton) findViewById(R.id.OptionsButton);
        AppCompatButton top = (AppCompatButton) findViewById(R.id.TopButton);
        AppCompatButton exit = (AppCompatButton) findViewById(R.id.ExitButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                //i.putExtra("fragment","Game");
                //startActivity(i);
                startActivityForResult(i,1);
                //Context.startActivityForResult(i, 1);
                //SetResult();
            }
        });

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TopActivity.class);
                i.putExtra("name", BestName);
                i.putExtra("time", time);
                //startActivity(i);
                startActivityForResult(i,2);
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, OptionsActivity.class);
                i.putExtra("name", name);
                //startActivity(i);
                startActivityForResult(i,3);
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
                finish();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //game
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                timeNow=data.getLongExtra("time",0);
                /*Toast.makeText(this, String.format("%01d", timeNow)+">"+String.format("%02d", time),
                        Toast.LENGTH_LONG).show();*/
                if(time<timeNow)
                {
                    BestName=name;
                    TextView textview = new TextView(this);
                    textview.setText(R.string.Congratulations);
                    textview.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    //textview.setBackgroundColor(R.color.colorDarkGreen);
                    Toast toast = new Toast(this);
                    toast.setView(textview);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                    /*Toast.makeText(this, R.string.Congratulations,
                            Toast.LENGTH_LONG).show();*/
                    time=timeNow;
                    Save();
                }
            }
            /*if (resultCode == Activity.RESULT_CANCELED) {
            }*/
        }
        //top
        if (requestCode == 2) {
            /*if(resultCode == Activity.RESULT_OK){
                time=data.getLongExtra("time",0);
            }*/
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
        //options
        if (requestCode == 3) {
            if(resultCode == Activity.RESULT_OK){
                name=data.getStringExtra("name");
                /*BestName=name;
                Toast.makeText(this, name,
                        Toast.LENGTH_LONG).show();*/
            }
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.salmat_team.offsetball/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.salmat_team.offsetball/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    /*    public void onButtonClick(View v)
    {
        if(v.getId()==R.id.StartButton)
        {
            Intent i=new Intent(MainActivity.this,GameActivity.class);
            i.putExtra("fragment","Game");
            startActivity(i);
        }

        if(v.getId()==R.id.OptionsButton)
        {
            Intent i=new Intent(MainActivity.this,GameActivity.class);
            i.putExtra("fragment","Options");
            startActivity(i);
        }
    }*/

}
