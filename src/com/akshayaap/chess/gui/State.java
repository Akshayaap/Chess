package com.akshayaap.chess.gui;

public class State {

    public static final int INVALID = -1;
    public static final int NORMAL = 0;
    public static final int SELECTED = 1;

    public static final int NONE_CHECK = 0;
    public static final int WHITE_CHECK = 100;
    public static final int BLACK_CHECK = 101;
    public static final int WHITE_CHECKMATE = 102;
    public static final int BLACK_CHECKMATE = 103;
    public static final int WHITE_STALEMATE = 104;
    public static final int BLACK_STALEMATE = 105;

    private int state = -1;

    private int chX = -1;
    private int chY = -1;

    private int chXprev = -1;
    private int chYprev = -1;

    private int chXNext = -1;
    private int chYNext = -1;

    private boolean turn = true;

    public State() {

    }

    public void reset() {
        this.chX = -1;
        this.chY = -1;

        this.chXprev = -1;
        this.chYprev = -1;

        this.chXNext = -1;
        this.chYNext = -1;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getChX() {
        return this.chX;
    }

    public void setChX(int chX) {
        this.chX = chX;
    }

    public int getChY() {
        return this.chY;
    }

    public void setChY(int chY) {
        this.chY = chY;
    }

    public int getChXprev() {
        return this.chXprev;
    }

    public void setChXprev(int chXprev) {
        this.chXprev = chXprev;
    }

    public int getChYNext() {
        return chYNext;
    }

    public void setChYNext(int chYNext) {
        this.chYNext = chYNext;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void toggleTurn() {
        this.turn = !this.turn;
    }

    public int getChYprev() {
        return this.chYprev;
    }

    public void setChYprev(int chYprev) {
        this.chYprev = chYprev;
    }

    public int getChXNext() {
        return this.chXNext;
    }

    public void setChXNext(int chXNext) {
        this.chXNext = chXNext;
    }
}