package com.akshayaap.chess.game;

public class ChessBoard {

    public final Tile[][] board;
    private final Move move=new Move();

    public  ChessBoard(){
        this.board=new Tile[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this.board[i][j]=new Tile(i,j);
            }
        }
    }

    public Piece getPiece(int x,int y){
        return this.board[x][y].getPiece();
    }

    public void resetBoard(){
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                this.board[i][j].setPiece(null);
            }
        }
    }

    public Move move(int chXPrev, int chYPrev, int x, int y) {
        if(this.board[chXPrev][chYPrev].getPiece()!=null){
            return this.board[chXPrev][chYPrev].getPiece().move(x, y);
        }
        this.move.reset();
        this.move.setState(Move.SOURCE_IS_EMPTY);
        this.move.setSource(chXPrev,chYPrev);
        this.move.setDestination(x,y);
        return this.move;
    }

    public Tile[][] getBoard() {
        return this.board;
    }
}
