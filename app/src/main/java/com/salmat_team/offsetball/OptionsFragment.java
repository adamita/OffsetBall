package com.salmat_team.offsetball;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Peti on 2016.04.26..
 */
public class OptionsFragment extends Fragment {
    private View root;
    private GameSpaceView gameView;


    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        root=inflater.inflate(R.layout.options_fragment,container,false);
        return root;
    }




}
