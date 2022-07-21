package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class King extends Piece {

    private boolean check = false;
    private boolean checkMate = false;
    private boolean stallMate = false;

    public King(int x, int y, Player player) {
        super(x, y, player);
        this.type = Piece.KING_TYPE;
        this.value = Piece.KING_VALUE;
    }


    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        if (x + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y] && (board[x + 1][y].getPiece() == null || (board[x + 1][y].getPiece() != null && board[x + 1][y].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y);
            }
        }
        if (x - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y] && (board[x - 1][y].getPiece() == null || (board[x - 1][y].getPiece() != null && board[x - 1][y].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y);
            }
        }
        if (y + 1 < 8) {
            if (!player.getThreatMap()[x][y + 1] && (board[x][y + 1].getPiece() == null || (board[x][y + 1].getPiece() != null && board[x][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x, y + 1);
            }
        }
        if (y - 1 >= 0) {
            if (!player.getThreatMap()[x][y - 1] && (board[x][y - 1].getPiece() == null || (board[x][y - 1].getPiece() != null && board[x][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x, y - 1);
            }
        }

        if (x + 1 < 8 && y + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y + 1] && (board[x + 1][y + 1].getPiece() == null || (board[x + 1][y + 1].getPiece() != null && board[x + 1][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y + 1);
            }
        }
        if (x + 1 < 8 && y - 1 >= 0) {
            if (!player.getThreatMap()[x + 1][y - 1] && (board[x + 1][y - 1].getPiece() == null || (board[x + 1][y - 1].getPiece() != null && board[x + 1][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y - 1);
            }
        }
        if (x - 1 >= 0 && y + 1 < 8) {
            if (!player.getThreatMap()[x - 1][y + 1] && (board[x - 1][y + 1].getPiece() == null || (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y + 1);
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y - 1] && (board[x - 1][y - 1].getPiece() == null || (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y - 1);
            }
        }
        updateCheck();
        updateCheckMate();
        updateStallMate();
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {
        this.resetMoveMap();
        if (x + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y] && (board[x + 1][y].getPiece() == null || (board[x + 1][y].getPiece() != null && board[x + 1][y].getPiece().getColor() != this.color))) {
                attackMap[x + 1][y] = true;
            }
        }
        if (x - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y] && (board[x - 1][y].getPiece() == null || (board[x - 1][y].getPiece() != null && board[x - 1][y].getPiece().getColor() != this.color))) {
                attackMap[x - 1][y] = true;
            }
        }
        if (y + 1 < 8) {
            if (!player.getThreatMap()[x][y + 1] && (board[x][y + 1].getPiece() == null || (board[x][y + 1].getPiece() != null && board[x][y + 1].getPiece().getColor() != this.color))) {
                attackMap[x][y + 1] = true;
            }
        }
        if (y - 1 >= 0) {
            if (!player.getThreatMap()[x][y - 1] && (board[x][y - 1].getPiece() == null || (board[x][y - 1].getPiece() != null && board[x][y - 1].getPiece().getColor() != this.color))) {
                attackMap[x][y - 1] = true;
            }
        }

        if (x + 1 < 8 && y + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y + 1] && (board[x + 1][y + 1].getPiece() == null || (board[x + 1][y + 1].getPiece() != null && board[x + 1][y + 1].getPiece().getColor() != this.color))) {
                attackMap[x + 1][y + 1] = true;
            }
        }
        if (x + 1 < 8 && y - 1 >= 0) {
            if (!player.getThreatMap()[x + 1][y - 1] && (board[x + 1][y - 1].getPiece() == null || (board[x + 1][y - 1].getPiece() != null && board[x + 1][y - 1].getPiece().getColor() != this.color))) {
                attackMap[x + 1][y - 1] = true;
            }
        }
        if (x - 1 >= 0 && y + 1 < 8) {
            if (!player.getThreatMap()[x - 1][y + 1] && (board[x - 1][y + 1].getPiece() == null || (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor() != this.color))) {
                attackMap[x - 1][y + 1] = true;
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y - 1] && (board[x - 1][y - 1].getPiece() == null || (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor() != this.color))) {
                attackMap[x - 1][y - 1] = true;
            }
        }
        return this.attackMap;
    }

    public boolean isCheck() {
        return this.check;
    }

    public boolean isCheckMate() {
        return this.checkMate;
    }

    public boolean updateCheck() {
        if (this.player.getThreatMap()[x][y]) {
            this.check = true;
        } else {
            this.check = false;
        }
        return this.check;
    }

    public boolean updateCheckMate() {
        this.checkMate = this.check && this.player.getLegalMoves() == 0;
        return this.checkMate;
    }

    public boolean updateStallMate() {
        return this.stallMate;
    }
}
