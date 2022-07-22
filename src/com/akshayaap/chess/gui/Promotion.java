package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.game.PromotionCallback;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Promotion implements PromotionCallback {

    public final JFrame prompt = new JFrame("Promot Pawn to...");
    public final JPanel white = new JPanel(new GridLayout(6, 1));
    public final JPanel black = new JPanel(new GridLayout(6, 1));

    private Player player;

    public Promotion() {
        prompt.setSize(new Dimension(60, 10));
        prompt.setVisible(false);
        prompt.setResizable(false);
        Tile tile;
        Listener listener = new Listener();
        for (int i = 0; i < 6; i++) {
            try {
                tile = new Tile(i, true);
                tile.addMouseListener(listener);
                white.add(tile);
                tile = new Tile(i, false);
                tile.addMouseListener(listener);
                black.add(tile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void prompt(Player player) {
        this.player = player;
        prompt.removeAll();
        if (player.getColor()) {
            prompt.add(white);
        } else {
            prompt.add(black);
        }
        prompt.validate();
        prompt.setVisible(true);
    }

    private class Tile extends JPanel {
        private static final Color DARK_SQUARE = new Color(50, 0, 20);
        private static final Color LIGHT_SQUARE = new Color(255, 255, 200);

        final int type;

        Tile(int type, boolean color) throws IOException {
            super(new GridBagLayout());
            setBackground(color ? DARK_SQUARE : LIGHT_SQUARE);
            this.type = type;
            String path = (color ? "white_" : "black_") + type + ".png";
            add(new JLabel(new ImageIcon(ImageIO.read(new File("res/" + path)).getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
            validate();
        }
    }

    private class Listener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            player.promot(Tile.this.type);
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
