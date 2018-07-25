package com.example.eltobgy.memorycardgame.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eltobgy.memorycardgame.R;
import com.example.eltobgy.memorycardgame.models.Card;
import com.example.eltobgy.memorycardgame.models.Game;
import com.example.eltobgy.memorycardgame.utlis.Helper;

/**
 * Created by Eltobgy on 20-Jul-18.
 */

public class GameStartScreen extends AppCompatActivity {
    private TextView textViewScoreValue;
    private ImageButton[] imageButtonArray;
    public static Card[] cardArray;
    Game mGame;
    private Chronometer chronometer;
    public final String TAG = GameStartScreen.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.showLog(TAG,"On Create.....");
        //TODO remove the testing screen and replace it with leveling up functionality.
        setContentView(R.layout.testing_screen);
    }
    //private void setGameBoard(int ) {

   // }
}

