package com.akshayaap.chess.game;

import javax.lang.model.element.NestingKind;
import java.io.IOException;

/**
 * class Player
 *
 * @Author Akshay
 */
public class Player {


    private final Pawn pawn0;
    private final Pawn pawn1;
    private final Pawn pawn2;
    private final Pawn pawn3;

    private final Pawn pawn4;
    private final Pawn pawn5;
    private final Pawn pawn6;
    private final Pawn pawn7;

    private final Knight knight0;
    private final Knight knight1;
    private final Bishop bishop0;
    private final Bishop bishop1;

    private final Rook rook0;
    private final Rook rook1;
    private final Queen queen;
    private final King king;


    /**
     * true for white and false for black
     */
    private boolean color;

    /**
     * Map for threats created by opponent Player
     */
    private boolean threatMap[][];

    /**
     * All possible attacks by this player
     */
    private boolean attackMap[][];

    /**
     * A Board from ChessBoard to Examine Various Situations
     */
    private Tile[][] board;

    /**
     * Indicated Check
     */
    private boolean check;

    /**
     * Indicates CheckMate
     */
    private boolean checkMate;

    /**
     * Indicates Stall
     */
    private boolean stallMate;

    private Player opponent;

    public Player(boolean color, Tile[][] board) {
        this.color = color;
        this.board = board;

        if(this.color){
            this.pawn0=new Pawn(1,0,this);
            this.pawn1=new Pawn(1,1,this);
            this.pawn2=new Pawn(1,2,this);
            this.pawn3=new Pawn(1,3,this);

            this.pawn4=new Pawn(1,4,this);
            this.pawn5=new Pawn(1,5,this);
            this.pawn6=new Pawn(1,6,this);
            this.pawn7=new Pawn(1,7,this);

            this.knight0=new Knight(0,1,this);
            this.knight1=new Knight(0,6,this);
            this.bishop0=new Bishop(0,2,this);
            this.bishop1=new Bishop(0,5,this);

            this.rook0=new Rook(0,0,this);
            this.rook1=new Rook(0,7,this);
            this.queen=new Queen(0,3,this);
            this.king=new King(0,4,this);
        }
        else {
            this.pawn0=new Pawn(6,0,this);
            this.pawn1=new Pawn(6,1,this);
            this.pawn2=new Pawn(6,2,this);
            this.pawn3=new Pawn(6,3,this);

            this.pawn4=new Pawn(6,4,this);
            this.pawn5=new Pawn(6,5,this);
            this.pawn6=new Pawn(6,6,this);
            this.pawn7=new Pawn(6,7,this);

            this.knight0=new Knight(7,1,this);
            this.knight1=new Knight(7,6,this);
            this.bishop0=new Bishop(7,2,this);
            this.bishop1=new Bishop(7,5,this);

            this.rook0=new Rook(7,0,this);
            this.rook1=new Rook(7,7,this);
            this.queen=new Queen(7,3,this);
            this.king=new King(7,4,this);
        }
    }

    /**
     * Sets the Opponent Player
     * @param player
     */
    public void setOpponent(Player player){
        this.opponent=player;
    }

    /**
     * Resets everything
     */
    public void reset() {

    }

    /**
     * Resets both maps
     */
    public void resetMaps() {

    }

    /**
     * Resets the AttackMap
     */
    public void resetAttackMap() {

    }

    /**
     * Resets the threatMap
     */
    public void resetThreatMap() {

    }

    /**
     * returns the check state
     *
     * @return
     */
    public boolean isCheck() {
        return check;
    }

    /**
     * Returns the checkMate state
     * @return
     */
    public boolean isCheckMate(){
        return this.checkMate;
    }

    /**
     * returns a Stallmate state
     * @return
     */
    public boolean isStallMate(){
        return this.stallMate;
    }

    public boolean[][] getThreatMap() {
        return threatMap;
    }

    public boolean[][] getAttackMap() {
        return attackMap;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public void setStallMate(boolean stallMate) {
        this.stallMate = stallMate;
    }

    public boolean getColor() {
        return this.color;
    }

    public void update() {
        this.pawn0.update();
        this.pawn1.update();
        this.pawn2.update();
        this.pawn3.update();

        this.pawn4.update();
        this.pawn5.update();
        this.pawn6.update();
        this.pawn7.update();

        this.knight0.update();
        this.knight1.update();
        this.bishop0.update();
        this.bishop1.update();

        this.rook0.update();
        this.rook1.update();
        this.queen.update();
        this.king.update();
    }
}

