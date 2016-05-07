package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Peti on 2016.05.07..
 */
public class TopActivity extends AppCompatActivity {

    String name="";
    long time=0L;
    int s;
    int m;
    TextView t;
    Handler timeH;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.top_acticity);

            name = getIntent().getExtras().getString("name",null);
            time=getIntent().getExtras().getLong("time",0L);

            AppCompatButton back = (AppCompatButton) findViewById(R.id.BackButton);
        TextView n=(TextView)findViewById(R.id.getName);
        n.setText(name);
        t=(TextView)findViewById(R.id.getBestTime);

        s=(int)time/1000;
        m=s/60;
        String st=("" + m + ":"
                + String.format("%02d", s) + ":"
                + String.format("%03d", (int)(time%1000)));
        t.setText(st);
        /*timeH = new Handler();
        t.post(new Runnable() {
            @Override
            public void run() {
                t.setText("" + m + ":"
                        + String.format("%02d", s) + ":"
                        + String.format("%03d", (int)(time%1000)));
                timeH.postDelayed(this,0);
            }
        });*/


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent returnIntent = getIntent();
                    setResult(Activity.RESULT_CANCELED,returnIntent);
                    finish();
                }
            });




    }
}
