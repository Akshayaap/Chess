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
        this.resetMap();

        int i = x + 1;
        int j=y;
        while (i < 8 ) {
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i++;
        }

        i = x - 1;
        j = y ;
        while (i  >= 0) {
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i--;
        }

        i = x ;
        j = y + 1;
        while (j < 8) {
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            j++;
        }

        i = x ;
        j = y - 1;
        while ( j >= 0) {
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            j--;
        }
    }
}
