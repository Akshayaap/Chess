package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Bishop extends Piece {

    public Bishop(int x, int y, Player player) {
        super(x, y, player);
        this.type = Piece.BISHOP_TYPE;
        this.value = Piece.BISHOP_VALUE;
    }

    /**
     * An Update method
     */
    @Override
    public void update() {
        this.resetMap();

        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8) {
            player.setAttackMap(i, j);
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i++;
            j++;
        }

        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
            player.setAttackMap(i, j);
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i++;
            j--;
        }

        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
            player.setAttackMap(i, j);
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i--;
            j++;
        }

        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            player.setAttackMap(i, j);
            if (board[i][j].getPiece() == null) {
                map[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    map[i][j] = true;
                }
                break;
            }
            i--;
            j--;
        }
    }
}
