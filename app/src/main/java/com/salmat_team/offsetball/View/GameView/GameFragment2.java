package com.salmat_team.offsetball.View.GameView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.salmat_team.offsetball.R;

/**
 * Created by Peti on 2016.05.05..
 */
public class GameFragment2 extends Fragment {

    private View root;
    private GameSpaceView gameView;

    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        root=inflater.inflate(R.layout.game_fragment2,container,false);
        gameView= (GameSpaceView) root.findViewById(R.id.gameSpaceView2);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(gameView!=null)
        {
            gameView.StartGame();
        }
    }

    @Override
    public void onPause() {
        super.onResume();
        if(gameView!=null)
        {
            gameView.PauseGame();
        }
    }
}
