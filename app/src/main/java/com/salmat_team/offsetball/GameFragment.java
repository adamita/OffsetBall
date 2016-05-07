package com.salmat_team.offsetball;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by adamita on 2016. 03. 20..
 */
public class GameFragment extends Fragment {

    private View root;
    private GameSpaceView gameView;

    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        root=inflater.inflate(R.layout.game_fragment,container,false);
        gameView= (GameSpaceView) root.findViewById(R.id.gameSpaceView);
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

    void BackTime()
    {

    }
}
