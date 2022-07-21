package com.akshayaap.chess.game;


public abstract class Piece {


    public static final int PAWN_VALUE = 1;
    public static final int PAWN_TYPE = 0;

    public static final int KNIGHT_VALUE = 3;
    public static final int KNIGHT_TYPE = 1;

    public static final int BISHOP_VALUE = 3;
    public static final int BISHOP_TYPE = 2;

    public static final int ROOK_VALUE = 5;
    public static final int ROOK_TYPE = 3;

    public static final int QUEEN_VALUE = 9;
    public static final int QUEEN_TYPE = 4;

    public static final int KING_VALUE = 10000;
    public static final int KING_TYPE = 5;


    protected int x;
    protected int y;

    protected int value;
    protected int type;

    protected boolean[][] moveMap;
    protected boolean[][] attackMap;
    protected boolean moved = false;
    protected boolean alive = true;

    protected int legalMoves = 0;
    protected boolean color;
    protected Player player;
    protected Tile[][] board;

    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.color = player.getColor();
        this.board = player.getBoard();
        this.moveMap = new boolean[8][8];
        this.resetMap();
        this.board[x][y].setPiece(this);
    }

    @Deprecated
    public abstract void reset();

    @Deprecated
    public abstract void update();

    public abstract boolean[][] updateMoveMap();

    public abstract boolean[][] updateAttackMap();

    public void resetMoveMap() {
        this.legalMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                moveMap[i][j] = false;
            }
        }
    }

    public void resetAttackMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                attackMap[i][j] = false;
            }
        }
    }

    @Deprecated
    public void resetMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.moveMap[i][j] = false;
            }
        }
    }

    public boolean getColor() {
        return this.color;
    }

    public int getType() {
        return this.type;
    }

    public Move move(int x, int y) {
        Move move = new Move();
        move.setSource(this.x, this.y);
        move.setDestination(x, y);
        Piece temp;
        int pX = this.x;
        int pY = this.y;
        if (this.moveMap[x][y]) {
            if (this.board[x][y].getPiece() != null) {
                move.setState(Move.CAPTURE_MOVE);
                this.board[x][y].getPiece().capture();
            } else {
                move.setState(Move.NORMAL_MOVE);
            }

            temp = this.board[x][y].getPiece();
            this.board[x][y].setPiece(this);
            this.board[this.x][this.y].setPiece(null);

            this.x = x;
            this.y = y;
            player.update();
            player.getOpponent().getOpponent().update();
            if (player.calCheck()) {
                move.setState(Move.ILLEGAL_MOVE);
                this.board[pX][pY].setPiece(this);
                this.board[x][y].setPiece(temp);
                this.x = pX;
                this.y = pY;
            }
            return move;
        } else {
            move.setState(Move.ILLEGAL_MOVE);
            return move;
        }
    }

    public void capture() {
        this.alive = false;
        this.x = -1;
        this.y = -1;
    }

    public boolean[][] getMoveMap() {
        return moveMap;
    }

    public int getLegalMoves() {
        return legalMoves;
    }

    public boolean isAlive() {
        return alive;
    }
}
