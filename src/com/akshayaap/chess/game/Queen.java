package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Queen extends Piece{

    /**
     *
     * @param x
     * @param y
     */
    public Queen(int x, int y,Player player){
        super(x,y,player);
        this.type=Piece.QUEEN_TYPE;
        this.value=Piece.QUEEN_VALUE;
    }

    /**
     * An Update Method
     */
    @Override
    public void update(){

    }
}
