package com.example.eltobgy.memorycardgame.models;

import java.io.Serializable;

/**
 * Created by Eltobgy on 20-Jul-18.
 */

public class Game implements Serializable {
    private int cardNum; //TODO give them numbers *8, eg: 1*8 =8 , 2*8=16 ...etc.
    private int cardRange;
    private int typeOfOperation; //TODO check the numbers and make the default no operation.
    private int gameType; //0-> figure, 1-> numerical , 2->operational.
    private int numFormat; //0-> eastern , 1-> western , else-> no num.

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public int getCardRange() {
        return cardRange;
    }

    public void setCardRange(int cardRange) {
        this.cardRange = cardRange;
    }

    public int getTypeOfOperation() {
        return typeOfOperation;
    }

    public void setTypeOfOperation(int typeOfOperation) {
        this.typeOfOperation = typeOfOperation;
    }

    public int getGameType() {
        return gameType;
    }

    public void setGameType(int gameType) {
        this.gameType = gameType;
    }

    public int getNumFormat() {
        return numFormat;
    }

    public void setNumFormat(int numFormat) {
        this.numFormat = numFormat;
    }
}
