package com.example.eltobgy.memorycardgame.activities;

import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.ToggleButton;

import com.example.eltobgy.memorycardgame.R;
import com.example.eltobgy.memorycardgame.fragments.LevelClearedDialog;
import com.example.eltobgy.memorycardgame.models.Card;
import com.example.eltobgy.memorycardgame.models.Game;
import com.example.eltobgy.memorycardgame.utlis.FigureFunctionality;
import com.example.eltobgy.memorycardgame.utlis.GameBasicFunctions;
import com.example.eltobgy.memorycardgame.utlis.Helper;

import java.util.ArrayList;
import java.util.Collections;
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
    int[] cardWidHigh = new int[2];
   // Intent myIntent = getIntent(); // gets the previously created intent
    public int cardNum ;
    public int cardRange;
    public int typeOfOperation;
    public int gameType ; //0 figure 1 numerical 2 operational
    public int numFormat = 0; //TODO edit this.
    Game mGame;
    Card mCard;
    Random random = new Random();
    private ToggleButton activeCard;
    ArrayList<Integer> cardFacesInt  = new ArrayList<Integer>();
    ArrayList<String> cardFacesStr = new ArrayList<String>();
    public static int maxRange;
    public static int pairsFound = 0 ;
    public static int uniqueCardCount;
    public static int[]rowsCols;
    public static int totalCardsNum;
    public int currentGameBack;
    Drawable currentCardBack;
    Drawable currentCardFront;
     ArrayList<Integer> availableCardFaces = new ArrayList<Integer>();
     ArrayList<String> availableCardFacesOp = new ArrayList<String>();
    @BindView(R.id.boardLayout)
    GridLayout boardLayout;
    @BindView(R.id.timer)
    Chronometer timer;
String s2="//";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Helper.showLog(TAG, "On Create.....");
        Intent myIntent = getIntent();
         cardNum = myIntent.getIntExtra("cardsNum", 1);
         cardRange = myIntent.getIntExtra("cardsRange", 0);
         typeOfOperation = myIntent.getIntExtra("typeOfOperation", 0);
         gameType = myIntent.getIntExtra("gameType", 0); //0 figure 1 numerical 2 operational
         numFormat = 0; //TODO edit this.

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
         currentCardFront = this.getResources().getDrawable(R.drawable.cardfront);
        mCard = new Card(false,currentGameBack);
        checkGameType();
    }
//TODO add more dynamic to this method.
    private void checkGameType() {
        initializeBoard();
        rowsCols = GameBasicFunctions.numOfRowsAndCols(cardNum);
        totalCardsNum =(rowsCols[0]* rowsCols[1]);
        uniqueCardCount =totalCardsNum /2;
Helper.showLog(TAG, "game type **"+String.valueOf(gameType));
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
        addButtonsToGrid(gameType);

    }
    public void initializeOperationGame() {
        initNumberOpGame(gameType);

        for (int i = 0; i < uniqueCardCount; i++) {
            //TODO adjust randomOp
            //TODO make it avaliableCardsFaces
            //Currently dealing with given parameter -static-. (( for testing ))
            String cardFace1 = GameBasicFunctions.generateCard(typeOfOperation, availableCardFaces.get(i), maxRange);
            String cardFace2 = GameBasicFunctions.generateCard(typeOfOperation, availableCardFaces.get(i), maxRange);

            cardFacesStr.add("("+i+")"+cardFace1);
            cardFacesStr.add("("+i+")"+cardFace2);
        }
        Collections.shuffle(cardFacesStr);
        addButtonsToGrid(gameType);
    }
    //range num max
    public void initializeNumericalGame() {
        initNumberOpGame(gameType);
        Collections.shuffle(cardFacesStr);
        addButtonsToGrid(gameType);



    }


    public void initializeFigureGame() {
        
        availableCardFaces = FigureFunctionality.initializeCards();

        selectCardPoolFigure();
           addButtonsToGrid(gameType);
    }
//checking

    public void initNumberOpGame(int gameType){
        uniqueCardCount = (rowsCols[0]* rowsCols[1])/2;
        maxRange = GameBasicFunctions.getMaxRange(cardRange);
        int tempNum;
        for (int i = 0 ; i <= uniqueCardCount ; i ++ ){
            //TODO check the num.

            Collections.sort(availableCardFaces);
            Helper.showLog(TAG,"avaliable card after sorting "+availableCardFaces);

            tempNum = GameBasicFunctions.generateRandomNumber(0, 2,maxRange, availableCardFaces);
            Helper.showLog(TAG,"temp num"+tempNum);
            //Helper.showLog(TAG,"avaliable card "+availableCardFaces);
            if(i>0){
            while(availableCardFaces.contains(tempNum)) {
                Helper.showLog(TAG,"Loop");
                tempNum = GameBasicFunctions.generateRandomNumber(0, 2,maxRange, availableCardFaces);
                Helper.showLog(TAG,"maxRange loop"+maxRange);
                Helper.showLog(TAG,"cardRange loop"+cardRange);
                Helper.showLog(TAG,"avaliable card loop"+availableCardFaces);
                Helper.showLog(TAG,"temp num loop"+tempNum);
              //  availableCardFaces.add(tempNum);
            }}
            //TODO remove this line (not optimized)
            availableCardFaces.add(tempNum);
            if (gameType == 1){
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
        Helper.showLog(TAG,"card faces size " +String.valueOf(availableCardFaces.size()));
        Helper.showLog(TAG,"AvailableCardFace "+ String.valueOf(random.nextInt(availableCardFaces.size())));
        return availableCardFaces.remove(random.nextInt(availableCardFaces.size()));
    }

    /**
     * Create the pool of card faces, randomly selected. Each one is added twice to ensure
     * exactly two copies of each card appear on the board
     */
    protected ArrayList<Integer> selectCardPoolFigure() {
        Helper.showLog(TAG, String.valueOf(availableCardFaces));
        Helper.showLog(TAG, String.valueOf(rowsCols[0]));
        uniqueCardCount = (rowsCols[0]* rowsCols[1])/2;

        Helper.showLog(TAG, "uniqueCardCount"+String.valueOf(uniqueCardCount));


        for (int i = 0; i < uniqueCardCount; i++) {
            int cardFace = getAvailableCardFace();
            Helper.showLog(TAG,"cardFace"+ String.valueOf(cardFace));
            cardFacesInt.add(cardFace);
            cardFacesInt.add(cardFace);
        }
        Helper.showLog(TAG, String.valueOf(cardFacesInt));
        Collections.shuffle(cardFacesInt);
        return cardFacesInt;
    }
    protected void addButtonsToGrid(final int gameType) {
        final String[] s1 = {""};
        final String[] temp = {""};
       cardWidHigh = calculateCardWidth(rowsCols);

        for (int i = 0; i < totalCardsNum ; i++) {
            Helper.showLog(TAG," i = "+i);
            final ToggleButton button = new ToggleButton(this);
            //TODO a different approach
            button.setTextOff("");
            button.setBackground(currentCardBack);
            button.setChecked(false);
            button.setId(i);
            if(gameType == 0){
            button.setTextOn("");
            button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {

                        //TODO remove this.
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                            button.setBackground(getDrawable(cardFacesInt
                                    .get(button.getId())));
                        }
                        checkAgainstActiveCard(button,"");
                    } else {
                        button.setBackground(currentCardBack);
                        button.setEnabled(true);
                    }
                }
            });}
            else{
                button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                        if (isChecked) {
                            //TODO remove this.
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                if(gameType == 1){
                                    Helper.showLog(TAG,"cardFaceStr size" +cardFacesStr.size());
                                    Helper.showLog(TAG,"cardFaceStr " +cardFacesStr);
                                button.setTextOn(cardFacesStr.get(button.getId()));}
                                else
                                {
                                    s1[0] = cardFacesStr.get(button.getId());
                                    Helper.showLog(TAG,"s1 before "+s1[0]);
                                    temp[0] = s1[0].substring(0,s1[0].indexOf(")")+1);
                                    String s2 = s1[0].substring(s1[0].indexOf(")")+1);
                                    s2.trim();
                                    button.setTextOn(s2);
                                }
                                //button.setTextS
                                button.setTextColor(ResourcesCompat.getColor(getResources(), R.color.Black, null));
                               //TODO adjust this.
                                button.setBackground(currentCardFront);
                            }
                            checkAgainstActiveCard(button, temp[0]);
                        } else {
                            button.setBackground(currentCardBack);
                            button.setEnabled(true);
                        }
                    }
                });
            }
            // addView(View child, int width, int height).
           // Helper.showLog(TAG, "button = "+button);
            boardLayout.addView(button, cardWidHigh[0], cardWidHigh[1]);
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
        }
        //
    }
    protected void checkAgainstActiveCard(final ToggleButton newFlip, final String s1) {
        if (activeCard != null) {
            //Found a pair
            if( (activeCard.getBackground().getConstantState().equals(newFlip.getBackground()
                    .getConstantState())&&(gameType ==0))||((activeCard.getTextOn().equals(newFlip.getTextOn() )&& gameType == 1))||
                    ((s1.equals(s2))&&(gameType==2))) {
                Helper.showLog(TAG,"s1 "+s1);
                Helper.showLog(TAG,"s2 "+s2);
                //TODO probably will need to add a handler.
                activeCard.setEnabled(false);
                newFlip.setEnabled(false);


                s2="//";
                pairsFound++;
               // activeCard.setVisibility(View.INVISIBLE);
                //newFlip.setVisibility(View.INVISIBLE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                       activeCard.setVisibility(View.INVISIBLE);
                       newFlip.setVisibility(View.INVISIBLE);
                        activeCard = null;
                    }
                }, 150);

                if (pairsFound == uniqueCardCount ) {
                    //TODO next level function.
                    timer.stop();
                    levelCompleted();

                }
            } else { //Did not find a pair
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        activeCard.toggle();
                        activeCard.setChecked(false);
                        activeCard.setBackground(currentCardBack);
                        activeCard.setEnabled(true);
                        activeCard = null;
                        newFlip.setChecked(false);
                        newFlip.setBackground(currentCardBack);
                        newFlip.setEnabled(true);
                    }
                }, 250);
            }
        } else { //First card to be flipped
            s2 = s1;
            activeCard = newFlip;
            activeCard.setChecked(true);
            activeCard.setEnabled(false);
        }
    }

    private void levelCompleted() {
        Helper.showToast(this,"You Win!");
        Bundle args = new Bundle();
       // args.putInt("score", scoreValue);
        args.putString("time", timer.getText().toString());
       // args.putString("type", type);
        DialogFragment winDialog = new LevelClearedDialog();
        winDialog.setArguments(args);
        winDialog.setCancelable(false);
        winDialog.show(getFragmentManager(), "Settings Dialog");
    }
    public void onComplete(Bundle callbackData) {
        String dialogName = callbackData.getString("dialog");

        if(dialogName.equals("nextlevel")) {
            Helper.showLog(TAG,"Level up");
        }
        else if(dialogName.equals("mainMenu")) {
            Intent myIntent = new Intent(this, TestingScreen.class);
            startActivity(myIntent);

        }
    }

    public void onBackPressed() {
        //TODO return to main or testing screen fn.
        //android back button has been pressed
    }
    public void initializeBoard() {
        rowsCols = GameBasicFunctions.numOfRowsAndCols(cardNum);
        boardLayout.setRowCount(rowsCols[0]);
        boardLayout.setColumnCount(rowsCols[1]);
        
    }
    //TODO check , double scalingFactor.
    //TODO test.
    protected int[] calculateCardWidth(int[] rowsCols) {
        //Getting the screen size.
        Display display = this.getWindowManager().getDefaultDisplay();
        ViewGroup.LayoutParams p = boardLayout.getLayoutParams();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        //cardWidHigh 0->width , 1 ->height.
        cardWidHigh[0] = (int) (width / (rowsCols[1] + .25));
        cardWidHigh[1] = (int) (height / (rowsCols[0] + 2));
       // textSize =
       // p.width = cardWidHigh[0];
       // p.height = cardWidHigh[1];
        //boardLayout.setLayoutParams(p);
        //boardLayout.getViewParent().invalidate();
     return cardWidHigh;
    }
}
