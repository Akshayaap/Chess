package com.akshayaap.chess.game;

/**
 * @Author Akshay
 * A class for state information
 */
public class State {

    public static final int INVALID = 0;
    public static final int NORMAL = 1;
    public static final int SELECTED = 2;

    public static final int WHITE_CHECK = -100;
    public static final int BLACK_CHECK = 101;
    public static final int WHITE_CHECKMATE = -102;
    public static final int BLACK_CHECKMATE = 103;
    public static final int WHITE_STALEMATE = -104;
    public static final int BLACK_STALEMATE = 105;


    private int chX;
    private int chY;

    private int chXPrev;
    private int chYPrev;

    private int chXNext;
    private int chYNext;

    private int state;
    private int checkState;
    private boolean turn;

    public State() {
        this.reset();
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

    public boolean isTurn() {
        return turn;
    }

    public void reset() {
        this.state = NORMAL;

        this.chX = -1;
        this.chY = -1;

        this.chXPrev = -1;
        this.chYPrev = -1;

        this.chXNext = -1;
        this.chYNext = -1;

        this.turn = true;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getChX() {
        return chX;
    }

    public void setChX(int chX) {
        this.chX = chX;
    }

    public int getChY() {
        return chY;
    }

    public void setChY(int chY) {
        this.chY = chY;
    }

    public int getChXPrev() {
        return chXPrev;
    }

    public void setChXPrev(int chXPrev) {
        this.chXPrev = chXPrev;
    }

    public int getChYPrev() {
        return chYPrev;
    }

    public void setChYPrev(int chYPrev) {
        this.chYPrev = chYPrev;
    }

    public int getChXNext() {
        return chXNext;
    }

    public void setChXNext(int chXNext) {
        this.chXNext = chXNext;
    }

    public int getChYNext() {
        return chYNext;
    }

    public void setChYNext(int chYNext) {
        this.chYNext = chYNext;
    }

    public void setChXY(int x, int y) {
        this.chX = x;
        this.chY = y;
    }

    public void setChXYPrev(int x, int y) {
        this.chXPrev = x;
        this.chYPrev = y;
    }

    public void setChXYNext(int x, int y) {
        this.chXNext = x;
        this.chYNext = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void toggleTurn() {
        this.turn = !this.turn;
    }

    public void fallBack() {
        this.state = NORMAL;

        this.chX = -1;
        this.chY = -1;

        this.chXPrev = -1;
        this.chYPrev = -1;

        this.chXNext = -1;
        this.chYNext = -1;

    }

}
