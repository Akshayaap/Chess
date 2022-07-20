package com.akshayaap.chess.game;

/**
 * @formatter:on
 */
public class Knight extends Piece {

    public Knight(int x, int y, Player player) {
        super(x, y, player);
        this.type = Piece.KNIGHT_TYPE;
        this.value = Piece.KNIGHT_VALUE;
    }


    @Override
    public void update() {
        this.resetMap();
        if (x + 2 < 8 && y + 1 < 8) {
            player.setAttackMap(x + 2, y + 1);
            if (board[x + 2][y + 1].getPiece() == null || board[x + 2][y + 1].getPiece().getColor() != this.color) {
                map[x + 2][y + 1] = true;
            }
        }
        if (x + 1 < 8 && y + 2 < 8) {
            player.setAttackMap(x + 1, y + 2);
            if (board[x + 1][y + 2].getPiece() == null || board[x + 1][y + 2].getPiece().getColor() != this.color) {
                map[x + 1][y + 2] = true;
            }
        }
        if (x + 2 < 8 && y - 1 >= 0) {
            player.setAttackMap(x + 2, y - 1);
            if (board[x + 2][y - 1].getPiece() == null || board[x + 2][y - 1].getPiece().getColor() != this.color) {
                map[x + 2][y - 1] = true;
            }
        }
        if (x + 1 < 8 && y - 2 >= 0) {
            player.setAttackMap(x + 1, y - 2);
            if (board[x + 1][y - 2].getPiece() == null || board[x + 1][y - 2].getPiece().getColor() != this.color) {
                map[x + 1][y - 2] = true;
            }
        }

        if (x - 2 >= 0 && y + 1 < 8) {
            player.setAttackMap(x - 2, y + 1);
            if (board[x - 2][y + 1].getPiece() == null || board[x - 2][y + 1].getPiece().getColor() != this.color) {
                map[x - 2][y + 1] = true;
            }
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            player.setAttackMap(x - 1, y - 2);
            if (board[x - 1][y - 2].getPiece() == null || board[x - 1][y - 2].getPiece().getColor() != this.color) {
                map[x - 1][y - 2] = true;
            }
        }
        if (x - 2 >= 0 && y - 1 >= 0) {
            player.setAttackMap(x - 2, y - 1);
            if (board[x - 2][y - 1].getPiece() == null || board[x - 2][y - 1].getPiece().getColor() != this.color) {
                map[x - 2][y - 1] = true;
            }
        }
        if (x - 1 >= 0 && y + 2 < 8) {
            player.setAttackMap(x - 1, y + 2);
            if (board[x - 1][y + 2].getPiece() == null || board[x - 1][y + 2].getPiece().getColor() != this.color) {
                map[x - 1][y + 2] = true;
            }
        }
    }
}
