package com.example.eltobgy.memorycardgame.utlis;

import android.os.Handler;

import com.example.eltobgy.memorycardgame.R;
import com.example.eltobgy.memorycardgame.activities.GameScreenActivity;
import com.example.eltobgy.memorycardgame.models.Card;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.String.valueOf;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

public class GameBasicFunctions {
    public final String TAG = GameBasicFunctions.class.getSimpleName();
    public static int randomNum;
    public static int maxRange;
    public int randomOp;
    final Handler delayHandler = new Handler();
    static String cardContent;
    static int[] factors = new int[0];
    static Random rand = new Random();
    public static int[] cardBacks = {
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
    };
    //TODO test this method.
    public static int getRandomBack(){
        ArrayList<Integer> ex = new ArrayList<Integer>();

        int cardBackId = generateRandomNumber(2,0,2,ex);
        return cardBacks[cardBackId];
    }
    public static Card[] cardArray = GameScreenActivity.cardArray;
    //TODO get the num of flipped and selected from the Grid object.
    //TODO the card???!
    //TODO the numOfFlippedCards? static? or return.
    //cardsNums starting from 1 as a min as 1*8 =8,
    public static int[]numOfRowsAndCols (int cardsNum){
        int[] rowsCols = new int [2];
        int totalCardsNum = cardsNum * 8;
        Helper.showLog("GameBasicFunctions", "total cards num " +String.valueOf(totalCardsNum));

        boolean perfectSqrt = Helper.checkingPerfectSquare(totalCardsNum);
       if(perfectSqrt){ rowsCols[0] = (int) Math.sqrt(totalCardsNum);
           rowsCols[1] = (int) Math.sqrt(totalCardsNum);
       }
       else{rowsCols[1] = 4;
       //TODO fix this to be more dynamic.
       rowsCols[0] = totalCardsNum/rowsCols[1];}
       Helper.showLog("GameBasicFunctions", String.valueOf(rowsCols[0]));
        Helper.showLog("GameBasicFunctions", String.valueOf(rowsCols[1]));

        return rowsCols;
    }

    //TODO implement how to win
    public static void gameOverWin() {
        //TODO moving to the next level
    }
    public static int getRandomWithExclusion(Random rnd, int start, int end, ArrayList<Integer> exclude) {
        int random = start + rnd.nextInt(end - start + 1 - exclude.size());
        Helper.showLog("Game basic functions : ","random = "+random);
        for (int ex : exclude) {
            if (random < ex) {
                break;
            }
            random++;
        }
        return random;
    }
    /**
     * @param range which depends on the selected level
     * @param ex
     * @return the random number
     */
    public static int generateRandomNumber(int typeOfGeneration , int num, int maxRange, ArrayList<Integer> ex) {

        switch (typeOfGeneration) {
            case 0: {
                //rand.nextInt(maxRange) + 2;
                randomNum =  getRandomWithExclusion(rand, 2, maxRange, ex);
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
    public static int[] numFactors(int number) {

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
            //    Helper.showLog("GameBasicFunction" , "num factors iiiii "+factors[i]);

            }
        }
        Helper.showLog("GameBasicFunction" , "num factors "+factors);
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
     * @param number -> the previously generated random
     * @param maxRange
     * @return the card content as a string.
     */
    public static String generateCard(int randomOp, int number, int maxRange)  {
        int num1;
        int num2;
        int num3; // for division
        ArrayList<Integer> ex = new ArrayList<Integer>();;
        //TODO generate a random op function.
        Helper.showLog("GameBasicFunctions","randopmOp"+randomOp);
        switch (randomOp) {
            //0 -> addition, 1-> subtraction, 2-> multiplication, 3-> division.
            case 0: {
                num1 = generateRandomNumber(1, number, maxRange, ex);
                num2 = number - num1;
                cardContent = valueOf(num1) + "+" + valueOf(num2);
                break;
            }
            case 1: {
                num1 = generateRandomNumber(2, number, maxRange, ex);
                num2 = num1 - number;
                cardContent = valueOf(num1) + "-" + valueOf(num2);
                break;
            }
            case 2: {
                //inorder to pick a random factor for the number.
                Helper.showLog("GameBasicFunctions" , "case 2  = "+factors);
                factors = numFactors(number);
                int factorIndex;
                if (factors.length > 1) {
                    factorIndex = generateRandomNumber(2, 0, factors.length,ex);
                    Helper.showLog("GameBasicFunctions" ,"factor index "+factorIndex);
                    num1 = factors[factorIndex];
                } else {
                    num1 = number;
                }
                num2 = number / num1;
                cardContent = valueOf(num1) + "*" + valueOf(num2);
                factors = null ;
                factors = new int[0];

                break;
            }
            case 3: {

                num3 = generateRandomNumber(0, number, maxRange,ex);
                int temp = num3 * number;
                factors = numFactors(temp);
                int factorIndex;
                factorIndex = generateRandomNumber(2, 0, factors.length,ex);
                //Inorder to make sure that the generated num is within the range, eg: 9 -> 81 / 3 = 27 (27 out of range)
                while (temp / factors[factorIndex] > maxRange || factors[factorIndex] > maxRange){
                    factorIndex = generateRandomNumber(2, 0, factors.length,ex);
                }
                num1 = factors[factorIndex];
                num2 = temp / num1;

                cardContent = valueOf(num1) + "*" + valueOf(num2) + "/" +valueOf(num3);
                break;

            }
        }
        return cardContent;
    }
    //TODO adjust later.
    public static int getMaxRange (int range){
        switch (range) {
            case 0: {
                maxRange = 9 ;
                break;
            }
            case 1: {

                maxRange = 20;
                break;
            }
            case 2: {
                maxRange = 32;
                break;
            }
            case 3:{
                maxRange =  100;
                break;
            }
        }


        return maxRange;}

}
