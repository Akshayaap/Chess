package com.akshayaap.chess.game;

import com.akshayaap.chess.gui.Promotion;

public class ChessGame {
    private final State state;
    private final ChessBoard board;
    private final Player playerWhite;
    private final Player playerBlack;
    private Move move = new Move();

    public ChessGame() {
        this.state = new State();
        this.board = new ChessBoard();

        this.playerBlack = new Player(false, this.board.getBoard());
        this.playerWhite = new Player(true, this.board.getBoard());
        this.playerBlack.setOpponent(this.playerWhite);
        this.playerWhite.setOpponent(this.playerBlack);

        this.playerWhite.updateAttackMap();
        this.playerBlack.updateAttackMap();
        this.playerWhite.updateMoveMap();
        this.playerBlack.updateMoveMap();
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }

    public Move onClick(int x, int y) {
        move.reset();
        switch (this.state.getState()) {
            case State.INVALID:
                move.setState(Move.INVALID_SELECTION);
                move.setSource(x, y);
                move.setDestination(-1, -1);
                break;
            case State.NORMAL:
                if (this.board.getPiece(x, y) == null) {
                    move.setState(Move.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                } else if (this.state.getTurn() == this.board.getPiece(x, y).getColor()) {
                    this.state.setChXYPrev(x, y);
                    this.state.setState(State.SELECTED);
                    this.state.setChXY(-1, -1);
                    this.state.setChXYNext(-1, -1);
                    move.setState(Move.SELECT_MOVE);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                    move.setTurn(this.state.getTurn());
                    move.setMap(board.getPiece(x, y).getMoveMap());
                } else {
                    move.setState(Move.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                }
                break;
            case State.SELECTED:
                if (this.board.getPiece(x, y) == null) {
                    this.state.setChXY(x, y);
                    move = this.board.moveTo(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                    this.state.fallBack();
                    move.setTurn(this.state.getTurn());
                    if (move.getState() != Move.ILLEGAL_MOVE && move.getState() != Move.SOURCE_IS_EMPTY && move.getState() != Move.INVALID_SELECTION && move.getState() != Move.NOT_APPLICABLE) {
                        this.state.toggleTurn();
                        update();
                    }
                } else {
                    if (this.board.getPiece(x, y).getColor() != this.state.getTurn()) {
                        this.state.setChXY(x, y);
                        move = this.board.moveTo(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                        this.state.fallBack();
                        move.setTurn(this.state.getTurn());
                        if (move.getState() != Move.ILLEGAL_MOVE && move.getState() != Move.SOURCE_IS_EMPTY && move.getState() != Move.INVALID_SELECTION && move.getState() != Move.NOT_APPLICABLE) {
                            move.setState(Move.CAPTURE_MOVE);
                            this.state.toggleTurn();
                            update();
                        }
                    } else {
                        this.state.fallBack();
                        this.state.setChXYPrev(x, y);
                        this.state.setState(State.SELECTED);

                        move.setState(Move.SELECT_MOVE);
                        move.setSource(this.state.getChXPrev(), this.state.getChYPrev());
                        move.setDestination(-1, -1);
                        move.setMap(board.getPiece(x, y).getMoveMap());
                    }
                }
                break;
            default:
                break;
        }
        this.board.printBord();
        return move;
    }

    public void update() {
        if (state.getTurn()) {
            playerBlack.updateAttackMap();
            playerWhite.updateMoveMap();
        } else {
            playerWhite.updateAttackMap();
            playerBlack.updateMoveMap();
        }
    }

    public Piece getPiece(int x, int y) {
        return this.board.getPiece(x, y);
    }

    public void setPromotionCallback(Promotion promotionCallback) {
        this.playerBlack.setPromotionCallback(promotionCallback);
        this.playerWhite.setPromotionCallback(promotionCallback);
    }

    public Player getPlayerBlack() {
        return this.playerBlack;
    }

    public void reset() {
        this.move.reset();
        this.state.reset();
        this.board.resetBoard();
        this.playerWhite.reset();
        this.playerWhite.reset();
    }
}
