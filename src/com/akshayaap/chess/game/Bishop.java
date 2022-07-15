package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Bishop extends Piece{

    public Bishop(int x,int y,Player player){
        super(x,y,player);
        this.type=Piece.BISHOP_TYPE;
        this.value=Piece.BISHOP_VALUE;
    }

    /**
     * An Update method
     */
    @Override
    public  void update(){

    }
}
