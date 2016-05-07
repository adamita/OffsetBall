package com.salmat_team.offsetball;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Peti on 2016.04.26..
 */
public class OptionsActivity extends AppCompatActivity {

    EditText ET;
    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_acticity);

        name = getIntent().getExtras().getString("name",null);

        ET =(EditText)findViewById(R.id.setName);
        Button OK =(Button)findViewById(R.id.OK);
        Button BACK =(Button)findViewById(R.id.Back);

        ET.setText(name);

        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=ET.getText().toString();
                getIntent().putExtra("name",name);
                setResult(Activity.RESULT_OK,getIntent());
                finish();
            }
        });

        BACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED,getIntent());
                finish();
            }
        });
    }
}
