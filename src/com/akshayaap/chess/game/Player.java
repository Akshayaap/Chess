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
    private final boolean color;

    /**
     * Map for threats created by opponent Player
     */
    private  boolean threatMap[][];
    /**
     * All possible attacks by this player
     */
    private final boolean attackMap[][]=new boolean[8][8];

    /**
     * A Board from ChessBoard to Examine Various Situations
     */
    private final Tile[][] board;

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

    private  Player opponent;

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

    public void setOpponent(Player player){
        this.opponent=player;
        this.threatMap=opponent.attackMap;
        update();
    }

    public void reset() {

    }

    public void resetMaps() {

    }

    public void resetAttackMap() {
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this.attackMap[i][j]=false;
            }
        }
    }

    public void resetThreatMap() {

    }

    public boolean isCheck() {
        return check;
    }

    public boolean isCheckMate(){
        return this.checkMate;
    }

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
        this.resetAttackMap();

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

    public void setAttackMap(int i, int j) {
        this.attackMap[i][j]=true;
    }

}

