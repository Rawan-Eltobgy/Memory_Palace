package com.example.eltobgy.memorycardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eltobgy.memorycardgame.R;
import com.example.eltobgy.memorycardgame.models.Card;
import com.example.eltobgy.memorycardgame.utlis.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Eltobgy on 20-Jul-18.
 */

public class TestingScreen extends AppCompatActivity {
    public static Card[] cardArray;
    public final String TAG = TestingScreen.class.getSimpleName();
    @BindView(R.id.et_cards_num)
    EditText etCardsNum;
    @BindView(R.id.linear_layout1)
    TextInputLayout linearLayout1;
    @BindView(R.id.et_number_range)
    EditText etNumberRange;
    @BindView(R.id.linear_layout2)
    TextInputLayout linearLayout2;
    @BindView(R.id.et_game_type)
    EditText etGameType;
    @BindView(R.id.linear_layout3)
    TextInputLayout linearLayout3;
    @BindView(R.id.et_operation_type)
    EditText etOperationType;
    @BindView(R.id.linear_layout4)
    TextInputLayout linearLayout4;
    @BindView(R.id.btn_start)
    Button startButton;
    @BindView(R.id.linearLayout)
    ConstraintLayout linearLayout;

    private TextView textViewScoreValue;
    private ImageButton[] imageButtonArray;
    private Chronometer chronometer;
    private int cardNum = 1; //TODO give them numbers *8, eg: 1*8 =8 , 2*8=16 ...etc.
    private int cardRange = 0;
    private int typeOfOperation = 0; //TODO check the numbers and make the default no operation.
    private int gameType = 0; //0-> figure, 1-> numerical , 2->operational.
    private int numFormat = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.showLog(TAG, "On Create.....");
        //TODO remove the testing screen and replace it with leveling up functionality.
        setContentView(R.layout.testing_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_start)
    public void onViewClicked() {
        if (!etCardsNum.equals("")){cardNum = Integer.parseInt(etCardsNum.getText().toString());}
        else if(!etNumberRange.equals("")){cardRange = Integer.parseInt(etCardsNum.getText().toString());}
        else if(!etGameType.equals("")){gameType = Integer.parseInt(etGameType.getText().toString());}
        else if(!etOperationType.equals("")){typeOfOperation = Integer.parseInt(etOperationType.getText().toString());}
        Intent myIntent = new Intent(this, GameScreenActivity.class);
        myIntent.putExtra("cardsNum",cardNum);
        myIntent.putExtra("cardsRange",cardRange);
        myIntent.putExtra("gameType",gameType);
        myIntent.putExtra("typeOfOperation",typeOfOperation);

        startActivity(myIntent);
    }

}

