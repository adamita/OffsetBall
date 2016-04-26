package com.salmat_team.offsetball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton start = (AppCompatButton) findViewById(R.id.StartButton);
        AppCompatButton options = (AppCompatButton) findViewById(R.id.OptionsButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,GameActivity.class);
                i.putExtra("fragment","Game");
                startActivity(i);
            }
        });

        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,GameActivity.class);
                i.putExtra("fragment","Options");
                startActivity(i);
            }
        });

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
