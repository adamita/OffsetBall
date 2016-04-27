package com.salmat_team.offsetball;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
<<<<<<< Updated upstream
    DrawGame DG;
    SensorManager manager;

    @Override
=======
    private GameSpaceView gameView;

>>>>>>> Stashed changes
    public View onCreateView (LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {
        root=inflater.inflate(R.layout.game_fragment,container,false);
        gameView= (GameSpaceView) root.findViewById(R.id.gameSpaceView);
        return root;
    }

    @Override
<<<<<<< Updated upstream
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.game_fragment);

        DG=(DrawGame)getView().findViewById(R.id.gamebox1);

        manager= (SensorManager) getActivity().getSystemService(getActivity().SENSOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        manager.registerListener(listener, manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
=======
    public void onResume() {
        super.onResume();
        if(gameView!=null)
        {
            gameView.StartGame();
        }
>>>>>>> Stashed changes
    }

    @Override
    public void onPause() {
<<<<<<< Updated upstream
        super.onPause();
        manager.unregisterListener(listener);
    }

    private SensorEventListener listener=new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) { }
        public void onSensorChanged(SensorEvent event) {
            float[] values=event.values;
            DG.BoxMove(values[0]);
            /* Ha nem az x tengelyen lévő mozgást néznénk
             DG.BoxMove(values[1]);
             DG.BoxMove(values[2]);
            */
        }


    };


=======
        super.onResume();
        if(gameView!=null)
        {
            gameView.PauseGame();
        }
    }
>>>>>>> Stashed changes
}
