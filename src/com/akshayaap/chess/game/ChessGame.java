package com.akshayaap.chess.game;

public class ChessGame {
    private final State state;
    private final ChessBoard board;
    private Player playerWhite;
    private Player playerBlack;

    public ChessGame() {
        this.state = new State();
        this.board = new ChessBoard();

        this.playerBlack=new Player(false,this.board.getBoard());
        this.playerWhite=new Player(true,this.board.getBoard());
        this.playerBlack.setOpponent(this.playerWhite);
        this.playerWhite.setOpponent(this.playerBlack);

    }

    public Move onClick(int x, int y) {
        Move move=new Move();
        switch (this.state.getState()) {
            case State.INVALID:
                move.reset();
                move.setState(Move.INVALID_SELECTION);
                move.setSource(x, y);
                move.setDestination(-1,-1);
                return move;
            case State.NORMAL:
                if (this.board.getPiece(x, y) == null) {
                    move.reset();
                    move.setState(Move.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                    return move;
                }
                if (this.state.getTurn() == this.board.getPiece(x, y).getColor()) {
                    this.state.setChXYPrev(x, y);
                    this.state.setState(State.SELECTED);
                    this.state.setChXY(-1, -1);
                    this.state.setChXYNext(-1, -1);


                    move.reset();
                    move.setState(Move.SELECT_MOVE);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                    move.setTurn(this.state.getTurn());
                    return move;
                } else {
                    move.reset();
                    move.setState(Move.INVALID_SELECTION);
                    move.setSource(x,y);
                    move.setDestination(-1,-1);
                    return move;
                }
                
            case State.SELECTED:
                if (this.board.getPiece(x, y) == null) {
                    this.state.setChXY(x,y);
                    move = this.board.move(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                    this.state.fallBack();
                    if(move.getState()!=Move.INVALID_MOVE&&move.getState()!=Move.SOURCE_IS_EMPTY){
                        this.state.toggleTurn();
                    }
                    return move;
                } else {
                    if (this.board.getPiece(x, y).getColor() != this.state.getTurn()) {
                        this.state.setChXY(x, y);
                        move = this.board.move(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                        this.state.fallBack();
                        if(move.getState()!=Move.INVALID_MOVE&&move.getState()!=Move.SOURCE_IS_EMPTY){
                            move.setState(Move.CAPTURE_MOVE);
                            this.state.toggleTurn();
                        }
                        return move;
                    }
                    else {
                        this.state.fallBack();
                        move.reset();
                        move.setState(Move.INVALID_MOVE);
                        move.setSource(this.state.getChXPrev(), this.state.getChYPrev());
                        move.setDestination(x, y);
                        return move;
                    }
                }
            default:
                break;
        }
        return new Move();
    }

    public Piece getPiece(int x, int y) {
        return this.board.getPiece(x, y);
    }

}
