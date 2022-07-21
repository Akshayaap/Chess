package com.akshayaap.chess.game;

public class ChessGame {
    private final State state;
    private final ChessBoard board;
    private Player playerWhite;
    private Player playerBlack;
    private Move move = new Move();

    public ChessGame() {
        this.state = new State();
        this.board = new ChessBoard();

        this.playerBlack = new Player(false, this.board.getBoard());
        this.playerWhite = new Player(true, this.board.getBoard());
        this.playerBlack.setOpponent(this.playerWhite);
        this.playerWhite.setOpponent(this.playerBlack);

        playerWhite.updateAttackMap();
        playerBlack.updateAttackMap();
        playerWhite.updateMoveMap();
        playerBlack.updateMoveMap();
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
                }
                if (this.state.getTurn() == this.board.getPiece(x, y).getColor()) {
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
                    move = this.board.move(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                    this.state.fallBack();
                    move.setTurn(this.state.getTurn());
                    if (move.getState() != Move.ILLEGAL_MOVE && move.getState() != Move.SOURCE_IS_EMPTY) {
                        this.state.toggleTurn();
                        update();
                    }
                } else {
                    if (this.board.getPiece(x, y).getColor() != this.state.getTurn()) {
                        this.state.setChXY(x, y);
                        move = this.board.move(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                        this.state.fallBack();
                        move.setTurn(this.state.getTurn());
                        if (move.getState() != Move.ILLEGAL_MOVE && move.getState() != Move.SOURCE_IS_EMPTY) {
                            move.setState(Move.CAPTURE_MOVE);
                            update();
                            this.state.toggleTurn();
                        }
                    } else {
                        this.state.fallBack();
                        this.state.setChXYPrev(x, y);
                        this.state.setState(State.SELECTED);
                        move.reset();
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
            this.playerBlack.update();
            this.playerWhite.update();
            if (playerWhite.isCheck()) {
                state.setCheckState(State.WHITE_CHECK);
                move.setCheckState(Move.WHITE_CHECK);
            }
            if (playerWhite.isCheckMate()) {
                state.setCheckState(State.WHITE_CHECKMATE);
                move.setCheckState(Move.WHITE_CHECKMATE);
            }
            if (playerWhite.isStallMate()) {
                state.setCheckState(State.WHITE_STALEMATE);
                move.setCheckState(Move.WHITE_STALMATE);
            }
        } else {
            this.playerWhite.update();
            this.playerBlack.update();
            if (playerBlack.isCheck()) {
                state.setCheckState(State.BLACK_CHECK);
                move.setCheckState(Move.BLACK_CHECK);
            }
            if (playerBlack.isCheckMate()) {
                state.setCheckState(State.BLACK_CHECKMATE);
                move.setCheckState(Move.BLACK_CHECKMATE);
            }
            if (playerBlack.isStallMate()) {
                state.setCheckState(State.BLACK_STALEMATE);
                move.setCheckState(Move.BLACK_STALMATE);
            }
        }
    }

    public Piece getPiece(int x, int y) {
        return this.board.getPiece(x, y);
    }

}
