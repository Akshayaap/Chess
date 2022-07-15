package com.akshayaap.chess.game;

public class Knight extends Piece{

    public Knight(int x,int y,Player player ){
        super(x,y,player);
        this.type=Piece.KNIGHT_TYPE;
        this.value=Piece.KNIGHT_VALUE;
    }

    /**
     * Updates the game status
     */
    @Override
    public void update(){

    }
}
