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

    /**
     * update method updates the status of king
     */
    @Override
    public void update() {
        this.legalMoves = 0;
        this.resetMap();

        if (x + 1 < 8) {
            player.setAttackMap(x + 1, y);
            if (!player.getThreatMap()[x + 1][y] && (board[x + 1][y].getPiece() == null ||
                    (board[x + 1][y].getPiece() != null &&
                            board[x + 1][y].getPiece().getColor() != this.color))) {
                map[x + 1][y] = true;
            }
        }
        if (x - 1 >= 0) {
            player.setAttackMap(x - 1, y);
            if (!player.getThreatMap()[x - 1][y] && (board[x - 1][y].getPiece() == null ||
                    (board[x - 1][y].getPiece() != null &&
                            board[x - 1][y].getPiece().getColor() != this.color))) {
                map[x - 1][y] = true;
            }
        }
        if (y + 1 < 8) {
            player.setAttackMap(x, y + 1);
            if (!player.getThreatMap()[x][y + 1] && (board[x][y + 1].getPiece() == null ||
                    (board[x][y + 1].getPiece() != null &&
                            board[x][y + 1].getPiece().getColor() != this.color))) {
                map[x][y + 1] = true;
            }
        }
        if (y - 1 >= 0) {
            player.setAttackMap(x, y - 1);
            if (!player.getThreatMap()[x][y - 1] && (board[x][y - 1].getPiece() == null ||
                    (board[x][y - 1].getPiece() != null &&
                            board[x][y - 1].getPiece().getColor() != this.color))) {
                map[x][y - 1] = true;
            }
        }

        if (x + 1 < 8 && y + 1 < 8) {
            player.setAttackMap(x + 1, y + 1);
            if (!player.getThreatMap()[x + 1][y + 1] && (board[x + 1][y + 1].getPiece() == null ||
                    (board[x + 1][y + 1].getPiece() != null &&
                            board[x + 1][y + 1].getPiece().getColor() != this.color))) {
                map[x + 1][y + 1] = true;
            }
        }
        if (x + 1 < 8 && y - 1 >= 0) {
            player.setAttackMap(x + 1, y - 1);
            if (!player.getThreatMap()[x + 1][y - 1] && (board[x + 1][y - 1].getPiece() == null ||
                    (board[x + 1][y - 1].getPiece() != null &&
                            board[x + 1][y - 1].getPiece().getColor() != this.color))) {
                map[x + 1][y - 1] = true;
            }
        }
        if (x - 1 >= 0 && y + 1 < 8) {
            player.setAttackMap(x - 1, y + 1);
            if (!player.getThreatMap()[x - 1][y + 1] && (board[x - 1][y + 1].getPiece() == null ||
                    (board[x - 1][y + 1].getPiece() != null &&
                            board[x - 1][y + 1].getPiece().getColor() != this.color))) {
                map[x - 1][y + 1] = true;
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            player.setAttackMap(x - 1, y - 1);
            if (!player.getThreatMap()[x - 1][y - 1] && (board[x - 1][y - 1].getPiece() == null ||
                    (board[x - 1][y - 1].getPiece() != null &&
                            board[x - 1][y - 1].getPiece().getColor() != this.color))) {
                map[x - 1][y - 1] = true;
            }
        }
        calLegalMoves();
        calCheck();
        calCheckMate();
        calStallMate();
    }

    public boolean calCheck() {
        this.check = player.getThreatMap()[x][y];
        this.player.setCheck(check);
        return this.check;
    }

    public boolean calCheckMate() {
        this.checkMate = this.legalMoves > 0 && this.check;
        this.player.setCheckMate(this.checkMate);
        return this.checkMate;
    }

    public boolean calStallMate() {
        this.stallMate = this.legalMoves == 0 && !this.check;
        player.setStallMate(this.stallMate);
        return this.stallMate;
    }

    public boolean isCheck() {
        return this.check;
    }

    public boolean isCheckMate() {
        return this.checkMate;
    }

}
