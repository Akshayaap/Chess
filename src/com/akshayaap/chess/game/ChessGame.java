package com.akshayaap.chess.game;

import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.gui.Promotion;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final State state;
    private final ChessBoard board;
    private final Player playerWhite;
    private final Player playerBlack;
    private Move move = new Move();
    private Logable logger;
    private List<Move> moveHistory = new ArrayList<>();

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

    @Deprecated
    public Move onClick(int x, int y) {
        move.reset();
        switch (this.state.getState()) {
            case State.INVALID:
                logger.log("Invalid state");
                move.setState(Move.INVALID_SELECTION);
                move.setSource(x, y);
                move.setDestination(-1, -1);
                break;
            case State.NORMAL:
                if (this.board.getPiece(x, y) == null) {
                    logger.log("No piece selected");
                    move.setState(Move.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                } else if (this.state.getTurn() == this.board.getPiece(x, y).getColor()) {
                    logger.log("Piece Selected:" + this.board.getPiece(x, y).getType() + " " + this.board.getPiece(x, y).getColor() + " " + x + " " + y);
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
                    logger.log("Invalid selection");
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
                        logger.log("Normal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                        this.state.toggleTurn();
                        logger.log("Turn:" + this.state.getTurn());
                        update();
                    } else {
                        logger.log("Illegal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                    }
                } else {
                    if (this.board.getPiece(x, y).getColor() != this.state.getTurn()) {
                        this.state.setChXY(x, y);
                        move = this.board.moveTo(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                        this.state.fallBack();
                        move.setTurn(this.state.getTurn());
                        if (move.getState() != Move.ILLEGAL_MOVE && move.getState() != Move.SOURCE_IS_EMPTY && move.getState() != Move.INVALID_SELECTION && move.getState() != Move.NOT_APPLICABLE) {
                            move.setState(Move.CAPTURE_MOVE);
                            logger.log("Capture Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                            this.state.toggleTurn();
                            update();
                        } else {
                            logger.log("Illegal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                        }
                    } else {
                        logger.log("Piece Selected:" + this.board.getPiece(x, y).getType() + " " + this.board.getPiece(x, y).getColor() + " " + x + " " + y);

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
            logger.log("Updating Black's aattackMap...");
            playerBlack.updateAttackMap();
            logger.log("Updating White's moveMap...");
            playerWhite.updateMoveMap();

            if (playerWhite.isCheckMate()) {
                logger.log("White is CheckMate");
                state.setCheckState(State.WHITE_CHECKMATE);
            } else if (playerWhite.isCheck()) {
                logger.log("White is Check");
                state.setCheckState(State.WHITE_CHECK);
            } else if (playerWhite.isStallMate()) {
                logger.log(Thread.currentThread().getStackTrace()[1] + "::White is Stalemate");
                state.setCheckState(State.WHITE_STALEMATE);
            } else {
                state.setCheckState(State.NONE_CHECK);
            }
            logger.log(Thread.currentThread().getStackTrace()[1] + "::White's Legal Moves:" + playerWhite.getLegalMoves());

        } else {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Updating White's aattackMap...");
            playerWhite.updateAttackMap();
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Updating Black's moveMap...");
            playerBlack.updateMoveMap();
            if (playerBlack.isCheckMate()) {
                logger.log(Thread.currentThread().getStackTrace()[1] + "::Black is CheckMate");
                state.setCheckState(State.BLACK_CHECKMATE);
            } else if (playerBlack.isCheck()) {
                logger.log(Thread.currentThread().getStackTrace()[1] + "::Black is Check");
                state.setCheckState(State.BLACK_CHECK);
            } else if (playerBlack.isStallMate()) {
                logger.log(Thread.currentThread().getStackTrace()[1] + "::Black is Stalemate");
                state.setCheckState(State.BLACK_STALEMATE);
            } else {
                state.setCheckState(State.NONE_CHECK);
            }
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Black's Legal Moves:" + playerBlack.getLegalMoves());
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
        logger.log("Resetting game");
        this.move.reset();
        this.state.reset();
        this.board.resetBoard();
        this.playerWhite.reset();
        this.playerBlack.reset();
        this.playerWhite.updateAttackMap();
        this.playerBlack.updateAttackMap();
        this.playerWhite.updateMoveMap();
        this.playerBlack.updateMoveMap();
    }

    public void setLogable(Logable logable) {
        this.logger = logable;
        playerWhite.setLogable(logable);
        playerBlack.setLogable(logable);
    }

    public State getState() {
        return this.state;
    }

    public Move move(Move move) {
        this.move.reset();
        this.move.setSource(move.getX1(), move.getY1());
        this.move.setDestination(move.getX2(), move.getY2());
        switch (move.getState()) {
            case Move.SELECT_MOVE:
                if (this.state.getTurn() == move.getTurn()) {
                    if (move.getTurn() == this.board.getPiece(move.getX1(), move.getY1()).getColor()) {
                        this.move.setState(Move.SELECT_MOVE);
                        this.move.setMap(this.board.getPiece(move.getX1(), move.getY1()).getMoveMap());
                    } else {
                        this.move.setState(Move.INVALID_SELECTION);
                    }
                    this.move.setTurn(this.state.getTurn());
                } else {
                    this.move.setState(Move.ILLEGAL_MOVE);
                    this.move.setTurn(move.getTurn());
                }
                break;
            case Move.NORMAL_MOVE:
                if (this.state.getTurn() == move.getTurn()) {
                    this.move = this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
                } else {
                    this.move.setState(Move.ILLEGAL_MOVE);
                }
                break;
            default:
                break;
        }
        this.update();
        return this.move;
    }

    public void playBlack(Move move) {
        Move tmp;
        if (!state.getTurn()) {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Playing Black's move");
            tmp = this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
            this.state.toggleTurn();
            update();
        } else {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Black's move is not applicable");
        }
    }

    public void playWhite(Move move) {
        if (state.getTurn()) {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Playing White's move");
            this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
            this.state.toggleTurn();
            update();
        } else {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::White's move is not applicable");
        }
    }
}
