package com.akshayaap.chess.game;

public class Move {

    public static final int NOT_APPLICABLE = -1;
    public static final int ILLEGAL_MOVE = 0;
    public static final int SOURCE_IS_EMPTY = 1;
    public static final int INVALID_SELECTION = 2;
    public static final int SELECT_MOVE = 13;
    public static final int NORMAL_MOVE = 10;
    public static final int CAPTURE_MOVE = 11;
    public static final int PROMOTION_MOVE = 12;


    public static final int WHITE_STALMATE = 100;
    public static final int BLACK_STALMATE = 101;
    public static final int WHITE_CHECK = 102;
    public static final int BLACK_CHECK = 103;
    public static final int WHITE_CHECKMATE = 104;
    public static final int BLACK_CHECKMATE = 105;

    public static final int TEST_MOVE = 200;


    private int state;

    private int checkState;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private boolean[][] map;

    private boolean turn;

    public Move() {
        this.reset();
    }

    public void setSource(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setDestination(int x2, int y2) {
        this.x2 = x2;
        this.y2 = y2;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getX1() {
        return x1;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getY2() {
        return y2;
    }

    public void reset() {
        this.state = NOT_APPLICABLE;
        this.checkState = NOT_APPLICABLE;
        this.x1 = -1;
        this.y1 = -1;
        this.x2 = -1;
        this.y2 = -1;

        this.map = null;
        this.turn = true;
    }

    public boolean[][] getMap() {
        return this.map;
    }

    public void setMap(boolean[][] map) {
        this.map = map;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getCheckState() {
        return checkState;
    }

    public void setCheckState(int checkState) {
        this.checkState = checkState;
    }

}
