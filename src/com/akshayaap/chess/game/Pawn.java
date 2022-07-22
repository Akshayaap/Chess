package com.akshayaap.chess.game;

public class Pawn extends Piece {

    private boolean promoted = false;
    private PromotionCallback callback;

    public Pawn(int x, int y, Player player, int index) {
        super(x, y, player, index);
        this.value = Piece.PAWN_VALUE;
        this.type = Piece.PAWN_TYPE;
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

    @Override
    public Move moveTo(int x, int y) {
        Move move = super.moveTo(x, y);
        if (!promoted) {
            int type = -1;
            if (this.color) {
                if (this.x == 7) {
                    type = callback.promot();
                    promot(type);
                }
            } else {
                if (this.x == 0) {
                    type = callback.promot();
                    promot(type);
                }
            }
        }
        move.setState(Move.PROMOTION_MOVE);
        return move;
    }

    public boolean promot(int type) {
        Piece piece;
        switch (type) {
            case -1:
                System.out.println("Aah Snap !");
            case 0:
                piece = this;
                System.out.println("WTF !!!");
                break;
            case 1:
                piece = new Knight(this.x, this.y, this.player, this.index);
                break;
            case 2:
                piece = new Bishop(this.x, this.y, this.player, this.index);
                break;
            case 3:
                piece = new Rook(this.x, this.y, this.player, this.index);
                break;
            case 4:
                piece = new Queen(this.x, this.y, this.player);
                break;
            case 5:
                System.out.println("WTF are you serious ?!?");
                piece = new King(this.x, this.y, this.player);
                break;
            default:
                System.out.println("Solar radiation detected !!");
                break;
        }
        this.promoted = true;
        return this.promoted;
    }

    public void setPromotionCallback(PromotionCallback callback) {
        this.callback = callback;
    }
}
