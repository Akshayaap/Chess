package com.akshayaap.chess;

import com.akshayaap.chess.gui.ChessGui;
import com.akshayaap.chess.gui.Promotion;

import java.io.IOException;

public class Chess {
    public static void main(String[] args) throws IOException {
        ChessGui chess = new ChessGui();
        Promotion promotion = new Promotion();
        promotion.prompt(chess.getGame().getPlayerWhite());
    }
}
