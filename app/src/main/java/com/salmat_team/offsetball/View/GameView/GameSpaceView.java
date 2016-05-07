package com.salmat_team.offsetball.View.GameView;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.salmat_team.offsetball.Game.Game;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameSpaceView extends View {

    private Game game;


    public GameSpaceView(Context context) {
        super(context);
        init(context, this);

    }

    public GameSpaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, this);
    }

    public GameSpaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameSpaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, this);
    }

    private void init(final Context context, final View view)
    {
        this.invalidate();
        this.post(new Runnable() {
            @Override
            public void run() {
                game=new Game(context,view,getMeasuredWidth(),getMeasuredHeight());
                StartGame();
            }
        });

    }

    public void StartGame()
    {
        if(game!=null)
            game.StartGame();
    }

    public void PauseGame()
    {
        if(game!=null)
            game.PauseGame();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(game!=null)
            game.Draw(canvas);

    }
}
