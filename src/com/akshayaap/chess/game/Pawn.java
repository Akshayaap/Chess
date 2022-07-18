package com.akshayaap.chess.game;

public class Pawn extends Piece{

    public Pawn(int x,int y,Player player){
        super(x,y,player);
        this.value=Piece.PAWN_VALUE;
        this.type=Piece.PAWN_TYPE;
    }
    @Override
    public void update(){
        this.resetMap();
        if(this.color){
            if(this.x==1){
                if(board[x+1][y].getPiece()==null) {
                    this.map[x + 1][y] = true;
                    if (board[x + 2][y].getPiece() == null) {
                        this.map[x + 2][y] = true;
                    }
                }
            }else{
                if(x+1<8){
                    if (board[x + 1][y].getPiece() == null) {
                        this.map[x+1][y]=true;
                    }
                    if(y+1<8){
                        if(board[x+1][y+1].getPiece()!=null){
                            if(!board[x+1][y+1].getPiece().getColor()){
                                this.map[x+1][y+1]=true;
                            }
                        }
                    }
                    if(y-1>=0){
                        if(board[x+1][y-1].getPiece()!=null){
                            if(!board[x+1][y-1].getPiece().getColor()){
                                this.map[x+1][y-1]=true;
                            }
                        }
                    }
                }
            }

        }
        else{
            if(this.x==6){
                if(board[x-1][y].getPiece()==null) {
                    this.map[x - 1][y] = true;
                    if (board[x - 2][y].getPiece() == null) {
                        this.map[x - 2][y] = true;
                    }
                }
            }else{
                if(x-1>=0){
                    if (board[x - 1][y].getPiece() == null) {
                        this.map[x-1][y]=true;
                    }
                    if(y+1<8){
                        if(board[x-1][y+1].getPiece()!=null){
                            if(!board[x-1][y+1].getPiece().getColor()){
                                this.map[x-1][y+1]=true;
                            }
                        }
                    }
                    if(y-1>=0){
                        if(board[x-1][y-1].getPiece()!=null){
                            if(board[x-1][y-1].getPiece().getColor()){
                                this.map[x-1][y-1]=true;
                            }
                        }
                    }
                }
            }

        }
    }
}
