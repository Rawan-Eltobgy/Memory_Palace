package com.example.eltobgy.memorycardgame.activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ToggleButton;

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
    public static int[] cardBacks = GameBasicFunctions.cardBacks;
    Intent myIntent = getIntent(); // gets the previously created intent
    public int cardNum = myIntent.getIntExtra("cardsNum", 1);
    public int cardRange = myIntent.getIntExtra("cardsRange", 0);
    public int typeOfOperation = myIntent.getIntExtra("typeOfOperation", 0);
    public int gameType = myIntent.getIntExtra("gameType", 0); //0 figure 1 numerical 2 operational
    public int numFormat = 0; //TODO edit this.
    Game mGame;
    Card mCard;
    Random random;
    private ToggleButton activeCard;
    ArrayList<Integer> cardFacesInt;
    ArrayList<String> cardFacesStr;
    public static int maxRange;
    public static int pairsFound = 0 ;
    public static int uniqueCardCount;
    public static int[]rowsCols;
    public static int totalCardsNum;
    public int currentGameBack;
    Drawable currentCardBack;
    private List<Integer> availableCardFaces;
    private List<String> availableCardFacesOp;
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
        /**              button.setBackground(getActivity().getResources().getDrawable(cardFaces
                                .get(button.getId())));
        checkAgainstActiveCard(button); **/
        currentGameBack = (GameBasicFunctions.getRandomBack());
         currentCardBack = this.getResources().getDrawable(currentGameBack);
        mCard = new Card(false,currentGameBack);
        checkGameType();
    }
//TODO add more dynamic to this method.
    private void checkGameType() {
        initializeBoard();
        totalCardsNum =(rowsCols[0]* rowsCols[1]);
        uniqueCardCount =totalCardsNum /2;

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
    public void mutualFunctionlity(int gameType){
       
        initNumberOpGame(gameType);
    }
    public void initializeOperationGame() {
        mutualFunctionlity(gameType);
        for (int i = 0; i < uniqueCardCount; i++) {
            //TODO adjust randomOp
            //TODO make it avaliableCardsFaces
            //Currently dealing with given parameter -static-. (( for testing ))
            String cardFace = GameBasicFunctions.generateCard(typeOfOperation, availableCardFaces.get(i), maxRange);
            cardFacesStr.add(cardFace);
            cardFacesStr.add(cardFace);
        }
        Collections.shuffle(cardFacesStr);

    }
    //range num max
    public void initializeNumericalGame() {
        mutualFunctionlity(gameType);
        Collections.shuffle(cardFacesStr);


    }


    public void initializeFigureGame() {
        
        availableCardFaces = FigureFunctionality.initializeCards();
        selectCardPoolFigure();
           addButtonsToGrid();
    }
//checking
    public void initNumberOpGame(int gameType){
        uniqueCardCount = (rowsCols[0]* rowsCols[1])/2;
        maxRange = GameBasicFunctions.getMaxRange(cardRange);
        for (int i = 0 ; i < uniqueCardCount ; i ++ ){
            int tempNum = GameBasicFunctions.generateRandomNumber(cardRange, 0,maxRange);
            while(availableCardFaces.contains(tempNum)) {
                availableCardFaces.set(i, tempNum);
            }
            //TODO remove this line (not optimized)
            availableCardFaces.set(i, tempNum);
            if (gameType == 2){
                //inorder to add it twice.
                cardFacesStr.add(String.valueOf(tempNum));
                cardFacesStr.add(String.valueOf(tempNum));
            }

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
    protected ArrayList<Integer> selectCardPoolFigure() {

        for (int i = 0; i < uniqueCardCount; i++) {
            int cardFace = getAvailableCardFace();
            cardFacesInt.add(cardFace);
            cardFacesInt.add(cardFace);
        }
        Collections.shuffle(cardFacesInt);
        return cardFacesInt;
    }
    protected void addButtonsToGrid( int columns, int rows, int cardWidth, int gameType) {
        for (int i = 0; i < totalCardsNum ; i++) {
            final ToggleButton button = new ToggleButton(this);
            //TODO a different approach
            button.setTextOff("");
            button.setTextOn("");
            button.setBackground(currentCardBack);
            button.setChecked(false);
            button.setId(i);
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
                        //TODO remove this.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            button.setBackground(getDrawable(cardFacesInt
                                    .get(button.getId())));
                        }
                        checkAgainstActiveCard(button);
                    } else {
                        button.setBackground(currentCardBack);
                        button.setEnabled(true);
                    }
                }
            });
            boardLayout.addView(button, cardWidth, cardWidth);
        }
    }
    protected void checkAgainstActiveCard(final ToggleButton newFlip) {
        if (activeCard != null) {
            //Found a pair
            if (activeCard.getBackground().getConstantState().equals(newFlip.getBackground()
                    .getConstantState())) {
                activeCard.setEnabled(false);
                newFlip.setEnabled(false);
                activeCard = null;
                pairsFound++;
                if (pairsFound == uniqueCardCount) {
                    //TODO next level function.
                    Helper.showToast(this,"You Win!");
                }
            } else { //Did not find a pair
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activeCard.toggle();
                        activeCard.setEnabled(true);
                        activeCard = null;
                        newFlip.setChecked(false);
                        newFlip.setBackground(currentCardBack);
                        newFlip.setEnabled(true);
                    }
                }, 350);
            }
        } else { //First card to be flipped
            activeCard = newFlip;
            activeCard.setEnabled(false);
        }
    }
    public void initializeBoard() {
        rowsCols = GameBasicFunctions.numOfRowsAndCols(cardNum);
        boardLayout.setRowCount(rowsCols[0]);
        boardLayout.setColumnCount(rowsCols[1]);
        
    }
}
