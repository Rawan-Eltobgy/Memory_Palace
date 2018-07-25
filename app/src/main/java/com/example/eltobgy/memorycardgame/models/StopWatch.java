package com.example.eltobgy.memorycardgame.models;

/**
 * Created by Eltobgy on 15-Jul-18.
 */

import java.io.Serializable;

public class StopWatch implements Serializable {

    private long elapsed;
    private long start;

    public void start() {
        elapsed = 0;
        start = System.currentTimeMillis();
    }

    public void pause() {
        elapsed += System.currentTimeMillis() - start;
    }

    public void resume() {
        start = System.currentTimeMillis();
    }

    public long elapsed() {
        return  System.currentTimeMillis() - start + elapsed;
    }
}
