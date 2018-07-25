package com.example.eltobgy.memorycardgame.models;

import java.util.Date;

/**
 * Created by Eltobgy on 14-Jul-18.
 */

public class Score {
    //TODO remove difficulity.

    public enum Level { EASY, MEDIUM, HARD }

    private String time;
    private String name;
    private Date date;
    private Level difficulty;

    public Score(){
        //default constructor
    }

    public Score(String time, String name, Date date, Level difficulty) {
        this.time = time;
        this.name = name;
        this.date = date;
        this.difficulty = difficulty;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public Level getDifficulty() {
        return difficulty;
    }

}

