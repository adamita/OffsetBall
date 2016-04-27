package com.salmat_team.offsetball;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by adamita on 2016. 04. 27..
 */
public class GameSpaceView extends View {

    private int width;
    private int height;
    private Game game;

    public GameSpaceView(Context context) {
        super(context);
        init(context);
    }

    public GameSpaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameSpaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GameSpaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context)
    {
        game=new Game(context,this,getWidth());
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
        game.Draw(canvas);

    }
}
