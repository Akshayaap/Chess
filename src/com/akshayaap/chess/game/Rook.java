package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Rook extends Piece{

    /**
     *
     * @param x
     * @param y
     */
    public Rook(int x,int y,Player player){
        super(x,y,player);
        this.type=Piece.ROOK_TYPE;
        this.value=Piece.ROOK_VALUE;
    }

    /**
     * An Update Method
     */
    @Override
    public void update(){

    }
}
