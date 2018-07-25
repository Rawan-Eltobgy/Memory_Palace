package com.example.eltobgy.memorycardgame.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

public class Card implements Serializable {


        public static final int STATE_HIDDEN   = 0;
        public static final int STATE_SELECTED = 1;
        public static final int STATE_SOLVED   = 2;


        private int state;
        private int animationSteps;
        private boolean flipped;
        private int row;
        private int col;
        private Bitmap frontCard;
        private Bitmap backCard;
        public Card(int state, boolean flipped, Bitmap frontCard, Bitmap backCard) {

            this.state  = state;
            this.frontCard = frontCard;
            this.backCard = backCard;
            this.flipped = false;
            this.row            = -1;
            this.col            = -1;
            this.animationSteps = 0;
        }


        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int getAnimationSteps() {
            return animationSteps;
        }

        public void setAnimationSteps(int animationCount) {
            this.animationSteps = animationCount;
        }

    public boolean isCardFlipped() {
        return flipped;
    }

    public void setCardFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    public Bitmap getFrontCard() {
        return frontCard;
    }

    public void setFrontCard(Bitmap frontCard) {
        this.frontCard = frontCard;
    }

    public Bitmap getBackCard() {
        return backCard;
    }

    public void setBackCard(Bitmap backCard) {
        this.backCard = backCard;
    }
}

