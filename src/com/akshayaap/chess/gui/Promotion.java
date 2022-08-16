package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.Piece;
import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.game.util.PromotionCallback;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

public class Promotion implements PromotionCallback {

    private static Object[] objsW;
    private static Object[] objsB;

    static {
        try {
            objsW = new Object[]
                    {new ImageIcon(read(new File("res/white_4.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/white_3.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/white_2.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/white_1.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/white_0.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/white_5.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH))};
            objsB = new Object[]
                    {new ImageIcon(read(new File("res/black_4.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/black_3.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/black_2.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/black_1.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/black_0.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                            new ImageIcon(read(new File("res/black_5.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH))};

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void prompt(Player player) throws IOException {
        Object p = JOptionPane.showInputDialog(null, "Promote Pawn to",
                "Promotion Time !", JOptionPane.QUESTION_MESSAGE, new ImageIcon(read(new File(player.getColor() ? "res/white_4.png" : "res/black_4.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH)),
                player.getColor() ? objsW : objsB, player.getColor() ? objsW[0] : objsB[0]);
        if (p == objsW[0] || p == objsB[0]) {
            player.promot(Piece.QUEEN_TYPE);
        } else if (p == objsW[1] || p == objsB[1]) {
            player.promot(Piece.ROOK_TYPE);
        } else if (p == objsW[2] || p == objsB[2]) {
            player.promot(Piece.BISHOP_TYPE);
        } else if (p == objsW[3] || p == objsB[3]) {
            player.promot(Piece.KNIGHT_TYPE);
        } else if (p == objsW[4] || p == objsB[4]) {
            player.promot(Piece.PAWN_TYPE);
        } else if (p == objsW[5] || p == objsB[5]) {
            player.promot(Piece.KING_TYPE);
        } else {
            System.out.println("Invalid Input");
            prompt(player);
        }
    }
}
