package com.akshayaap.chess.game;

public class Pawn extends Piece{

    public Pawn(int x,int y,Player player){
        super(x,y,player);
        this.value=Piece.PAWN_VALUE;
        this.type=Piece.PAWN_TYPE;
    }
    @Override
    public void update(){
        if(moved){

        }
    }
}
