package com.example.eltobgy.memorycardgame.models;

import java.io.Serializable;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

public class Grid implements Serializable {

    private  int      rows;
    private  int      cols;
    private final Card[][] data;

    private int selectedRow;
    private int selectedCol;

    private int  cardHeight;
    private int  cardWidth;

    private int numFlippedCards;
    private int remainingCards;
    private int selectedCards[] = new int[2];

    public static final Grid EMPTY = new Grid(0, 0, 0, 0, 0);

    public Grid(int rows, int cols, int canvasHeight, int canvasWidth , int totalNumCards) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Card[rows][cols];

        this.selectedRow = -1;
        this.selectedCol = -1;

        selectedCards[0] = -1;
        selectedCards[1] = -1;

        numFlippedCards = 0;
        remainingCards = totalNumCards;

        setCanvasHeight(canvasHeight);
        setCanvasWidth(canvasWidth);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setRows(int rows){
        this.rows =rows;
    }

    public void setCols(int cols){
        this.cols =cols;
    }
    public void setCanvasHeight(int canvasHeight) {
        if (rows > 0) {
            cardHeight = canvasHeight / rows;
        } else {
            cardHeight = 0;
        }
    }

    public void setCanvasWidth(int canvasWidth) {
        if (cols > 0) {
            cardWidth = canvasWidth / cols;
        } else {
            cardWidth = 0;
        }
    }

    public int getCardHeight() {
        return cardHeight;
    }

    public int getCardWidth() {
        return cardWidth;
    }

    public Card getCardAt(int row, int col) {
        if ((row >= 0 && row < rows) && (col >= 0 && col < cols)) {
            return data[row][col];
        }
        return null;
    }

    public void setCardAt(Card card, int row, int col) {
        if ((row >= 0 && row < rows) && (col >= 0 && col < cols)) {
            card.setRow(row);
            card.setCol(col);
            data[row][col] = card;
        }
    }

    public Card getCardAtPoint(float x, float y) {
        int row = (int) y / cardHeight;
        int col = (int) x / cardWidth;
        return getCardAt(row, col);
    }

    public void selectCard(Card card) {
        card.setState(Card.STATE_SELECTED);
        this.selectedRow = card.getRow();
        this.selectedCol = card.getCol();
    }

    public void clearSelectedCard() {
        Card selected = getSelectedTile();
        if (selected != null) {
            selected.setState(Card.STATE_HIDDEN);
        }
        this.selectedRow = -1;
        this.selectedCol = -1;
    }

    public Card getSelectedTile() {
        return getCardAt(selectedRow, selectedCol);
    }

    public int getNumFlippedCards() {
        return numFlippedCards;
    }

    public void setNumFlippedCards(int numFlippedCards) {
        this.numFlippedCards = numFlippedCards;
    }

    public int getRemainingCards() {
        return remainingCards;
    }

    public void setRemainingCards(int remainingCards) {
        this.remainingCards = remainingCards;
    }

    public int[] getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(int[] selectedCards) {
        this.selectedCards = selectedCards;
    }
}
