package com.akshayaap.chess.game;


public class Player {

    private final Piece[][] pieces;

    private final boolean color;
    private final boolean[][] attackMap = new boolean[8][8];
    private final Tile[][] board;
    private int legalMoves;
    private boolean[][] threatMap;
    private boolean check;
    private boolean checkMate;
    private boolean stallMate;
    private Player opponent;
    private boolean[][] moveMap = new boolean[8][8];

    public Player(boolean color, Tile[][] board) {
        this.color = color;
        this.board = board;
        this.pieces = new Piece[6][];

        pieces[0] = new Piece[8];
        for (int j = 0; j < 8; j++) {
            pieces[0][j] = new Pawn(this.color ? 1 : 6, j, this);
        }

        pieces[1] = new Piece[2];
        pieces[1][0] = new Knight(this.color ? 0 : 7, 1, this);
        pieces[1][1] = new Knight(this.color ? 0 : 7, 6, this);

        pieces[2] = new Piece[2];
        pieces[2][0] = new Bishop(this.color ? 0 : 7, 2, this);
        pieces[2][1] = new Bishop(this.color ? 0 : 7, 5, this);

        pieces[3] = new Piece[2];
        pieces[3][0] = new Rook(this.color ? 0 : 7, 0, this);
        pieces[3][1] = new Rook(this.color ? 0 : 7, 7, this);

        pieces[4] = new Piece[1];
        pieces[4][0] = new Queen(this.color ? 0 : 7, 4, this);

        pieces[5] = new Piece[1];
        pieces[5][0] = new King(this.color ? 0 : 7, 3, this);

        resetMoveMap();
        resetAttackMap();
    }

    private static boolean[][] arrayOR_ACC(boolean[][] dest, boolean[][] src) {
        if (dest.length != src.length || dest[0].length != src[0].length) {
            System.out.println("Can't Perform OR and Accumulation Operation Due to different Dimensions");
        }
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] |= src[i][j];
            }
        }
        return dest;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public int getLegalMoves() {
        return legalMoves;
    }

    public boolean[][] getMoveMap() {
        return moveMap;
    }

    public boolean isColor() {
        return color;
    }

    public void reset() {

    }

    public void resetAttackMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.attackMap[i][j] = false;
            }
        }
    }

    public void resetMoveMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.moveMap[i][j] = false;
            }
        }
    }

    /**
     * Do not use for god' sake
     */
    @Deprecated
    public void resetThreatMap() {

    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheckMate() {
        return this.checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
    }

    public boolean isStallMate() {
        return this.stallMate;
    }

    public void setStallMate(boolean stallMate) {
        this.stallMate = stallMate;
    }

    public boolean[][] getThreatMap() {
        return threatMap;
    }

    public void setThreatMap(boolean[][] threatMap) {
        this.threatMap = threatMap;
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

    public void setOpponent(Player player) {
        this.opponent = player;
        this.threatMap = opponent.getAttackMap();
    }

    public boolean getColor() {
        return this.color;
    }

    /**
     * Do not use this function for god’ sake
     */
    @Deprecated
    public void update() {
        this.resetAttackMap();
        for (int i = 0; i < 6; i++) {
            if (i == 0) {
                for (int j = 0; j < 8; j++) {
                    if (pieces[i][j].isAlive()) {
                        pieces[i][j].update();
                    }
                }
            } else if (i == 4 || i == 5) {
                if (pieces[i][0].isAlive()) {
                    pieces[i][0].update();
                }
            } else {
                for (int j = 0; j < 2; j++) {
                    if (pieces[i][j].isAlive()) {
                        pieces[i][j].update();
                    }
                }
            }
        }
    }

    public void updateAttackMap() {
        this.resetAttackMap();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isAlive()) {
                    arrayOR_ACC(this.attackMap, pieces[i][j].updateAttackMap());
                }
            }
        }
    }

    public void updateMoveMap() {
        this.resetMoveMap();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isAlive()) {
                    arrayOR_ACC(this.moveMap, pieces[i][j].updateMoveMap());
                }
            }
        }
        this.updateLegalMoves();
        updateCheck();
        updateCheckMate();
        updateStallMate();
    }

    @Deprecated
    public boolean calCheck() {
        this.check = ((King) pieces[5][0]).calCheck();
        return check;
    }

    public boolean updateCheck() {
        this.check = ((King) pieces[5][0]).updateCheck();
        return check;
    }

    public boolean updateCheckMate() {
        this.checkMate = ((King) pieces[5][0]).updateCheckMate();
        return this.checkMate;
    }

    public boolean updateStallMate() {
        this.stallMate = ((King) pieces[5][0]).updateStallMate();
        return this.stallMate;
    }

    public int updateLegalMoves() {
        for (boolean[] i : this.moveMap) {
            for (boolean j : i) {
                if (j) {
                    this.legalMoves++;
                }
            }
        }
        return this.legalMoves;
    }

}

