package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class King extends Piece{
    /**
     * Check and CheckMate
     */
    private boolean check=false;
    private boolean checkMate=false;

    /**
     * @param x
     * @param y
     */
    public King(int x,int y,Player player){
        super(x,y,player);
        this.type=Piece.KING_TYPE;
        this.value=Piece.KING_VALUE;
    }

    /**
     * update method updates the status of king
     */
    @Override
    public void update(){

    }

    /**
     * checks if king is in check or not
     * @return
     */
    public boolean isCheck(){
        return this.check;
    }

    /**
     * check if king is in checkmate or not
     * @return
     */
    public boolean isCheckMate(){
        return this.checkMate;
    }
}
