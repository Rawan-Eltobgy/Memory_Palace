package com.example.eltobgy.memorycardgame.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.GridLayout;

import com.example.eltobgy.memorycardgame.R;
import com.example.eltobgy.memorycardgame.models.Card;
import com.example.eltobgy.memorycardgame.models.Game;
import com.example.eltobgy.memorycardgame.utlis.FigureFunctionality;
import com.example.eltobgy.memorycardgame.utlis.GameBasicFunctions;
import com.example.eltobgy.memorycardgame.utlis.Helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eltobgy on 26-Jul-18.
 */

public class GameScreenActivity extends AppCompatActivity {
    public final String TAG = GameScreenActivity.class.getSimpleName();

    public static Card[] cardArray;
    Intent myIntent = getIntent(); // gets the previously created intent
    public int cardNum = myIntent.getIntExtra("cardsNum", 1);
    public int cardRange = myIntent.getIntExtra("cardsRange", 0);
    public int typeOfOperation = myIntent.getIntExtra("typeOfOperation", 0);
    public int gameType = myIntent.getIntExtra("gameType", 0);
    public int numFormat = 0; //TODO edit this.
    Game mGame;
    Random random;

    ArrayList<Integer> cardFaces;
    public static int maxRange;
    public static int uniqueCardCount;
    int[]rowsCols;
    private List<Integer> availableCardFaces;
    @BindView(R.id.boardLayout)
    GridLayout boardLayout;
    @BindView(R.id.timer)
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.showLog(TAG, "On Create.....");
        //TODO remove the testing screen and replace it with leveling up functionality.
        setContentView(R.layout.fragment_board);
        ButterKnife.bind(this);
        //initializing the game.
        mGame = new Game(cardNum,cardRange, typeOfOperation,gameType,numFormat);

        checkGameType();
    }
//TODO add more dynamic to this method.
    private void checkGameType() {
        switch (gameType) {
            case 0: {
                initializeFigureGame();
                break;
            }
            case 1: {
                maxRange = GameBasicFunctions.getMaxRange(cardRange);
                initializeNumericalGame();
                break;
            }
            case 2: {
                initializeOperationGame();
                break;
            }
    }}
    public void mutualFunctionlity(){
        initializeBoard();
        initNumberOpGame();
    }
    public void initializeOperationGame() {
        mutualFunctionlity();
    }

    //range num max
    public void initializeNumericalGame() {
        mutualFunctionlity();


    }


    public void initializeFigureGame() {
        initializeBoard();
        availableCardFaces = FigureFunctionality.initializeCards();

    }
//checking
    public void initNumberOpGame(){
        uniqueCardCount = (rowsCols[0]* rowsCols[1])/2;
        for (int i = 0 ; i < uniqueCardCount ; i ++ ){
            int tempNum = GameBasicFunctions.generateRandomNumber(cardRange, 0,maxRange);
            while(availableCardFaces.contains(tempNum)) {
                availableCardFaces.set(i, tempNum);
            }
            //TODO remove this line (not optimized)
            availableCardFaces.set(i, tempNum);

        }
    }

    /**
     * Return a card image that has not yet been added to the deck, removing it from the pool of
     * available cards.
     */
    private int getAvailableCardFace() {
        return availableCardFaces.remove(random.nextInt(availableCardFaces.size()));
    }

    /**
     * Create the pool of card faces, randomly selected. Each one is added twice to ensure
     * exactly two copies of each card appear on the board
     */
    protected ArrayList<Integer> selectCardPool() {

        for (int i = 0; i < uniqueCardCount; i++) {
            int cardFace = getAvailableCardFace();
            cardFaces.add(cardFace);
            cardFaces.add(cardFace);
        }
        Collections.shuffle(cardFaces);
        return cardFaces;
    }


    public void initializeBoard() {
        rowsCols = GameBasicFunctions.numOfRowsAndCols(cardNum);
        boardLayout.setRowCount(rowsCols[0]);
        boardLayout.setColumnCount(rowsCols[1]);
        
    }
}
