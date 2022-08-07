package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.Piece;
import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.game.PromotionCallback;

import javax.swing.*;

public class Promotion implements PromotionCallback {
    @Override
    public void prompt(Player player) {
        String p = JOptionPane.showInputDialog("Enter LAtter to Promote Pawn to");
        switch (p) {
            case "Q":
                player.promot(Piece.QUEEN_TYPE);
                break;
            case "N":
                player.promot(Piece.KNIGHT_TYPE);
                break;
            case "K":
                player.promot(Piece.KING_TYPE);
                break;
            case "B":
                player.promot(Piece.BISHOP_TYPE);
                break;
            case "R":
                player.promot(Piece.ROOK_TYPE);
                break;
            case "P":
                player.promot(Piece.PAWN_TYPE);
                break;

            default:
                System.out.println("Invalid Input");
                break;
        }

    }
}
