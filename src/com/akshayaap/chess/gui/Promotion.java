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
        prompt.setLayout(new BorderLayout());
        prompt.setSize(new Dimension(600, 100));
        prompt.setVisible(false);
        //prompt.setResizable(false);
        for (int i = 0; i < 6; i++) {
            try {
                white.add(new Tile(i, true), BorderLayout.CENTER);
                black.add(new Tile(i, false), BorderLayout.CENTER);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        white.validate();
        black.validate();
        prompt.validate();
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
        prompt.revalidate();
        prompt.repaint();
        prompt.setVisible(true);
    }

    private class Panel extends JPanel {
        Tile[] tiles = new Tile[6];

        public Panel() {
            for (int i = 0; i < 6; i++) {
                try {
                    tiles[i] = new Tile(i, true);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            setPreferredSize(new Dimension(100, 100));
            setVisible(true);
            setLayout(new GridLayout(6, 1));
            for (int i = 0; i < 6; i++) {
                add(tiles[i]);
            }
        }
    }

    private class Tile extends JPanel {
        private static final Dimension TILE_DIMENSION = new Dimension(100, 100);
        private static final Color DARK_SQUARE = new Color(50, 0, 20);
        private static final Color LIGHT_SQUARE = new Color(255, 255, 200);

        final int type;

        Tile(int type, boolean color) throws IOException {
            super(new GridBagLayout());
            setPreferredSize(TILE_DIMENSION);
            setBackground(color ? DARK_SQUARE : LIGHT_SQUARE);
            setVisible(true);
            this.type = type;
            String path = (color ? "white_" : "black_") + type + ".png";
            add(new JLabel(new ImageIcon(ImageIO.read(new File("res/" + path)).getScaledInstance(60, 60, Image.SCALE_SMOOTH))));
            addMouseListener(new Listener());
            validate();
        }

        private class Listener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                Promotion.this.player.promot(Tile.this.type);
                Promotion.this.player = null;
                Promotion.this.prompt.setVisible(false);
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
}
