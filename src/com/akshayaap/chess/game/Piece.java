package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */

import com.akshayaap.chess.gui.ChessGui;

/**
 * Piece Class
 */
public abstract class Piece {

    /**
     * Static final variables for each pieces
     */
    public static final int PAWN_VALUE=1;
    public static final int PAWN_TYPE=0;

    public static final int KNIGHT_VALUE=3;
    public static final int KNIGHT_TYPE=1;

    public static final int BISHOP_VALUE=3;
    public static final int BISHOP_TYPE=2;

    public static final int ROOK_VALUE=5;
    public static final int ROOK_TYPE=3;

    public static final int QUEEN_VALUE=9;
    public static final int QUEEN_TYPE=4;

    public static final int KING_VALUE=10000;
    public static final int KING_TYPE=5;


    protected int x;
    protected int y;

    protected int value;
    protected int type;

    protected boolean[][] map;
    protected boolean moved=false;

    protected boolean color;
    protected Player player;
    protected Tile[][] board;

    public Piece(int x,int y,Player player){
        this.x=x;
        this.y=y;
        this.player=player;
        this.color=player.getColor();
        this.board=player.getBoard();
        this.map=new boolean[8][8];
        this.resetMap();
        this.board[x][y].setPiece(this);
    }

    /**
     * Deprecated
     */
    @Deprecated
    public void reset(){

    }

    public abstract void update();

    public void resetMap(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this.map[i][j]=false;
            }
        }
    }

    public boolean getColor(){
        return this.color;
    }

    public int getType(){
        return this.type;
    }

    public Move move(int x, int y) {
        Move move =new Move();
        move.setSource(this.x,this.y);
        move.setDestination(x,y);
        if(this.map[x][y]){
            if(this.board[x][y].getPiece()!=null){
                move.setState(Move.CAPTURE_MOVE);
            }
            else{
                move.setState(Move.NORMAL_MOVE);
            }
            this.board[x][y].setPiece(this);
            this.board[this.x][this.y].setPiece(null);
            this.x=x;
            this.y=y;
            return move;
        }
        else {
            move.setState(Move.INVALID_MOVE);
            return move;
        }
    }

    public boolean[][] getMap() {
        return map;
    }
    public void updateAttackMap(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(map[i][j]){
                    player.setAttackMap(i,j);
                }
            }
        }
    }
}
