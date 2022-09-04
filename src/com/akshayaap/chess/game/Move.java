package com.akshayaap.chess.game;

import com.akshayaap.chess.game.util.ChessState;

public class Move {

    private ChessState state;

    private ChessState checkState;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private boolean[][] map;

    private boolean turn;
    private Piece capturedPiece;  //if nay

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

    public ChessState getState() {
        return state;
    }

    public void setState(ChessState state) {
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
        this.state = ChessState.NOT_APPLICABLE;
        this.checkState = ChessState.CHECK_NONE;
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

    public ChessState getCheckState() {
        return checkState;
    }

    public void setCheckState(ChessState checkState) {
        this.checkState = checkState;
    }

}
