package com.akshayaap.chess.game;

public class Pawn extends Piece {

    public Pawn(int x, int y, Player player) {
        super(x, y, player);
        this.value = Piece.PAWN_VALUE;
        this.type = Piece.PAWN_TYPE;
    }

    @Override
    @Deprecated
    public void reset() {

    }

    @Deprecated
    @Override
    public void update() {
        this.resetMap();
        if (this.color) {
            if (x + 1 < 8) {
                if (board[x + 1][y].getPiece() == null) {
                    moveMap[x + 1][y] = true;
                    this.legalMoves++;
                    if (x == 1 && board[x + 2][y].getPiece() == null) {
                        moveMap[x + 2][y] = true;
                        this.legalMoves++;
                    }
                }
                if (y + 1 < 8) {
                    player.setAttackMap(x + 1, y + 1);
                    if (board[x + 1][y + 1].getPiece() != null && !board[x + 1][y + 1].getPiece().getColor()) {
                        moveMap[x + 1][y + 1] = true;
                        this.legalMoves++;
                    }
                }
                if (y - 1 >= 0) {
                    player.setAttackMap(x + 1, y - 1);
                    if (board[x + 1][y - 1].getPiece() != null && !board[x + 1][y - 1].getPiece().getColor()) {
                        moveMap[x + 1][y - 1] = true;
                        this.legalMoves++;
                    }
                }
            }
        } else {
            if (x - 1 >= 0) {
                if (board[x - 1][y].getPiece() == null) {
                    moveMap[x - 1][y] = true;
                    this.legalMoves++;
                    if (x == 6 && board[x - 2][y].getPiece() == null) {
                        moveMap[x - 2][y] = true;
                        this.legalMoves++;
                    }
                }
                if (y + 1 < 8) {
                    player.setAttackMap(x - 1, y + 1);
                    if (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor()) {
                        moveMap[x - 1][y + 1] = true;
                        this.legalMoves++;
                    }
                }
                if (y - 1 >= 0) {
                    player.setAttackMap(x - 1, y - 1);
                    if (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor()) {
                        moveMap[x - 1][y - 1] = true;
                        this.legalMoves++;
                    }
                }
            }
        }
    }

    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        if (this.color) {
            if (x + 1 < 8) {
                if (board[x + 1][y].getPiece() == null) {
                    testForMoveMap(x + 1, y);
                    if (x == 1 && board[x + 2][y].getPiece() == null) {
                        testForMoveMap(x + 2, y);
                    }
                }
                if (y + 1 < 8) {
                    if (board[x + 1][y + 1].getPiece() != null && !board[x + 1][y + 1].getPiece().getColor()) {
                        testForMoveMap(x + 1, y + 1);
                    }
                }
                if (y - 1 >= 0) {
                    if (board[x + 1][y - 1].getPiece() != null && !board[x + 1][y - 1].getPiece().getColor()) {
                        testForMoveMap(x + 1, y - 1);
                    }
                }
            }
        } else {
            if (x - 1 >= 0) {
                if (board[x - 1][y].getPiece() == null) {
                    testForMoveMap(x - 1, y);
                    if (x == 6 && board[x - 2][y].getPiece() == null) {
                        testForMoveMap(x - 2, y);
                    }
                }
                if (y + 1 < 8) {
                    if (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor()) {
                        testForMoveMap(x - 1, y + 1);
                    }
                }
                if (y - 1 >= 0) {
                    if (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor()) {
                        testForMoveMap(x - 1, y - 1);
                    }
                }
            }
        }
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {
        this.resetAttackMap();
        if (this.color) {
            if (x + 1 < 8) {
                if (y + 1 < 8) {
                    attackMap[x + 1][y + 1] = true;
                }
                if (y - 1 >= 0) {
                    attackMap[x + 1][y - 1] = true;
                }
            }
        } else {
            if (x - 1 >= 0) {
                if (y + 1 < 8) {
                    attackMap[x - 1][y + 1] = true;
                }
                if (y - 1 >= 0) {
                    attackMap[x - 1][y - 1] = true;
                }
            }
        }
        return this.attackMap;
    }
}
