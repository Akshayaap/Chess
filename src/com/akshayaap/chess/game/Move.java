package com.akshayaap.chess.game;

public class Move {

    public static final int INVALID_MOVE = -1;
    public static final int NORMAL_MOVE = 100;
    public static final int CAPTURE_MOVE = 101;
    public static final int ENPASSANT_MOVE = 102;
    public static final int PROMOTION_MOVE = 103;
    public static final int CASTLING_MOVE = 104;
    public static final int CHECK_MOVE = 105;
    public static final int CHECKMATE_MOVE = 106;
    public static final int STALEMATE_MOVE = 107;
    public static final int DRAW_MOVE = 109;
    public static final int RESIGN_MOVE = 110;
    public static final int TIMEOUT_MOVE = 111;
    public static final int UNKNOWN_MOVE = 112;
    public static final int GAME_OVER_MOVE = 113;
    public static final int GAME_START_MOVE = 114;
    public static final int GAME_END_MOVE = 115;
    public static final int GAME_RESET_MOVE = 116;
    public static final int GAME_UNDO_MOVE = 117;
    public static final int GAME_REDO_MOVE = 118;
    public static final int GAME_SAVE_MOVE = 119;
    public static final int GAME_LOAD_MOVE = 120;

    public static final int SELECT_MOVE = 200;
    public static final int SOURCE_IS_EMPTY = 201;
    public static final int INVALID_SELECTION = 202;

    private int state;
    private int x1;
    private int y1;
    private int x2;
    private int y2;

    private boolean map[][];

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
    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
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
    public void reset(){
        this.state = INVALID_MOVE;
        this.x1 = -1;
        this.y1 = -1;
        this.x2 = -1;
        this.y2 = -1;

        this.map=null;
        this.turn=true;
    }

    public void setMap(boolean [][] map){
        this.map=map;
    }
    public boolean[][] getMap(){
        return this.map;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }
    public boolean getTurn() {
        return turn;
    }
}
