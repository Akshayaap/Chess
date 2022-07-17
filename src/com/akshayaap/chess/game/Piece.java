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



    /**
     * x and y positions of piece
     */
    protected int x;
    protected int y;
    /**
     * value of piece
     */
    protected int value;
    /**
     * Type identifies the piece
     */
    protected int type;

    /**
     * Individual AttackMap
     */
    protected boolean[][] map;

    /**
     * Variable to indicate whether Piece is moved or not
     */
    protected boolean moved=false;

    /**
     * Color is color of player
     * true is for white and false is for black
     */
    protected boolean color;

    protected Tile[][] board;

    public Piece(int x,int y,Player player){
        this.x=x;
        this.y=y;
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
        for(int i=0;i<ChessConfig.DEFAULT_SQUARE;i++){
            for(int j=0;j<ChessConfig.DEFAULT_SQUARE;j++){
                this.map[i][j]=true;
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
}
