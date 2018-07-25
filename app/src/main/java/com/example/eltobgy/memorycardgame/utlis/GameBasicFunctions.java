package com.example.eltobgy.memorycardgame.utlis;

import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

import com.example.eltobgy.memorycardgame.activities.GameStartScreen;
import com.example.eltobgy.memorycardgame.models.Card;

import java.util.Random;

import static java.lang.String.valueOf;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

public class GameBasicFunctions {
    public final String TAG = GameBasicFunctions.class.getSimpleName();
    public int randomNum;
    public int randomOp;
    final Handler delayHandler = new Handler();
    String cardContent;
    int[] factors = new int[0];
    Random rand = new Random();
    public static Card[] cardArray = GameStartScreen.cardArray;
    //TODO get the num of flipped and selected from the Grid object.
    //TODO the card???!
    //TODO the numOfFlippedCards? static? or return.
    public void flipCardUp(int card, int numFlippedCards, int[] selectedCards) {
        if(numFlippedCards <= 2){
            if(selectedCards[0] == -1) {
                selectedCards[0] = card;
            }
            else if(selectedCards[1] == -1) {
                selectedCards[1] = card;
            }

            numFlippedCards ++;
        }
    }

    public void flipCardDown(int card, int numFlippedCards, int[] selectedCards) {
        if(numFlippedCards >= 1){
            if(selectedCards[0] == card) {
                selectedCards[0] = -1;
            }
            else if(selectedCards[1] == card) {
                selectedCards[1] = -1;
            }

            numFlippedCards --;
        }
    }
    public void cardsNotMatched(final int[] cardsFlipped, final ImageButton[] imageButtonArray){
        //cards don't match


        //flip the cards back and prevent input during the delay
        for(ImageButton imageButton : imageButtonArray){
            imageButton.setEnabled(false);
        }

        delayHandler.postDelayed(new Runnable() {
            public void run() {
                cardArray[cardsFlipped[0]].setCardFlipped(false);
                cardArray[cardsFlipped[1]].setCardFlipped(false);
                imageButtonArray[cardsFlipped[0]].setImageBitmap(cardArray[cardsFlipped[0]].getBackCard());
                imageButtonArray[cardsFlipped[1]].setImageBitmap(cardArray[cardsFlipped[1]].getBackCard());

                flipCardDown(cardsFlipped[0] , 2, cardsFlipped);
                flipCardDown(cardsFlipped[1],2,cardsFlipped);

                for(ImageButton imageButton : imageButtonArray){
                    imageButton.setEnabled(true);
                }

                //reduce the players score
                //reduceScore();
            }
        }, 1000);
    }
    //Use handler to wait set invisible
    public void removingMatchedCards(final int[] cardsFlipped, final ImageButton[] imageButtonArray){

        //remove cards and prevent input during the delay
        for(ImageButton imageButton : imageButtonArray){
            imageButton.setEnabled(false);
        }

        delayHandler.postDelayed(new Runnable() {
            public void run() {
                imageButtonArray[cardsFlipped[0]].setVisibility(View.INVISIBLE);
                imageButtonArray[cardsFlipped[1]].setVisibility(View.INVISIBLE);

                flipCardDown(cardsFlipped[0] , 2, cardsFlipped);
                flipCardDown(cardsFlipped[1],2,cardsFlipped);

                for(ImageButton imageButton : imageButtonArray){
                    imageButton.setEnabled(true);
                }

                //check to see if the player has won
                gameOverWin();
            }
        }, 1000);
    }
    //TODO implement how to win
    private void gameOverWin() {
        /** if(remainingCards == 0) && (time>= required) {

         }**/
    }

    /**
     * @param range which depends on the selected level
     * @return the random number
     */
    public int generateRandomNumber(int range, int num, int maxRange) {

        switch (range) {
            case 0: {
                randomNum = rand.nextInt(maxRange) + 2;
                break;
            }
            case 1: {
                //customized range for addition.
                randomNum = rand.nextInt(num) + 2;
                break;
            }
            case 2: {//customized range for subtraction..
                randomNum = rand.nextInt(maxRange) + num;
                break;
            }
        }

        return randomNum;
    }
//TODO adjust this method.
    public boolean doCardsMatch(String card1, String card2, int remainingCards) {
        if(card1.equals(card2)){
            remainingCards -= 2;
           // mediaPlayerMatch.start();
            Helper.showLog(TAG,"Matched");
            return true;
        }
        else {
            //mediaPlayerMiss.start();
            Helper.showLog(TAG,"Not Matched");
            return false;
        }
    }
    /**
     * @param number
     * @return an array of factors of number.
     */
    public int[] numFactors(int number) {

        if (number % 2 == 0) {
            while (number % 2 == 0) {
                factors = Helper.addElementToArray(factors, 2);
                number /= 2;
            }
        }
        for (int i = 3; i * i <= number; i += 2)//odd numbers only
        {
            while (number % i == 0) {
                number /= i;
                factors = Helper.addElementToArray(factors, i);
            }
        }
        return factors;
    }

    /**
     * maxRange -> the maximum number in this level category.
     *number -> the previously randomly generated number.
     * @return the num of operation randomly.
     */
    public int generateRandomOperation() {
        randomOp = rand.nextInt(0) + 1;
        return randomOp;
    }

    /**
     * @param randomOp
     * @param number
     * @param maxRange
     * @return the card content as a string.
     */
    public String generateCard(int randomOp, int number, int maxRange) {
        int num1;
        int num2;
        int num3; // for division
        switch (randomOp) {
            //0 -> addition, 1-> subtraction, 2-> multiplication, 3-> division.
            case 0: {
                num1 = generateRandomNumber(1, number, maxRange);
                num2 = number - num1;
                cardContent = valueOf(num1) + "+" + valueOf(num2);
            }
            case 1: {
                num1 = generateRandomNumber(2, number, maxRange);
                num2 = num1 - number;
                cardContent = valueOf(num1) + "-" + valueOf(num2);
            }
            case 2: {
                //inorder to pick a random factor for the number.
                factors = numFactors(number);
                int factorIndex;
                if (factors.length > 1) {
                    factorIndex = generateRandomNumber(2, 0, factors.length);
                    num1 = factors[factorIndex];
                } else {
                    num1 = number;
                }
                num2 = number / num1;
                cardContent = valueOf(num1) + "*" + valueOf(num2);
                break;
            }
            case 3: {

                num3 = generateRandomNumber(0, number, maxRange);
                int temp = num3 * number;
                factors = numFactors(temp);
                int factorIndex;
                factorIndex = generateRandomNumber(2, 0, factors.length);
                //Inorder to make sure that the generated num is within the range, eg: 9 -> 81 / 3 = 27 (27 out of range)
                while (temp / factors[factorIndex] > maxRange || factors[factorIndex] > maxRange){
                    factorIndex = generateRandomNumber(2, 0, factors.length);
                }
                num1 = factors[factorIndex];
                num2 = temp / num1;

                cardContent = valueOf(num1) + "*" + valueOf(num2) + "/" +valueOf(num3);
                break;

            }
        }
        return cardContent;
    }
}
