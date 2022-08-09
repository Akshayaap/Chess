package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.CaptureCallBack;
import com.akshayaap.chess.game.Piece;
import com.akshayaap.chess.game.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CaptureWindow extends JPanel implements CaptureCallBack {
    private Player player;

    public CaptureWindow(Player player) {
        this.player = player;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));
        this.setPreferredSize(new Dimension(150, 200));
    }

    @Override
    public void capture(Piece piece) {
        String path = "res/" + ((piece.getColor() ? "white_" : "black_")) + piece.getType() + ".png";
        try {
            add(new JLabel(new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
            validate();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
