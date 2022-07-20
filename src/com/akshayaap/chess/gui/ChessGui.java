package com.akshayaap.chess.gui;

import com.akshayaap.chess.Chess;
import com.akshayaap.chess.game.ChessGame;
import com.akshayaap.chess.game.Move;
import com.akshayaap.chess.game.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessGui {
    private static final Dimension OUTER_FRAME_DIMENSION;

    static {
        OUTER_FRAME_DIMENSION = new Dimension(800, 800);
    }

    private final JFrame gameFrame;
    private final JMenuBar menuBar;
    private final State state;
    private final ChessGame game;
    private final BoardPanel boardPanel;
    private Move move;

    public ChessGui() throws IOException {
        this.game = new ChessGame();
        this.gameFrame = new JFrame("A Chess Game !");
        this.gameFrame.setLayout(new BorderLayout());
        this.menuBar = populateMenuBar();
        this.gameFrame.setJMenuBar(this.menuBar);
        this.gameFrame.setSize(OUTER_FRAME_DIMENSION);
        this.gameFrame.setVisible(true);
        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.boardPanel = new BoardPanel();
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.pack();

        this.state = new State();
        this.render();

    }

    private JMenuBar populateMenuBar() {
        final JMenuBar menubar = new JMenuBar();
        menubar.add(createFileMenu());
        return menubar;
    }

    private JMenu createFileMenu() {
        final JMenu fileMenu = new JMenu("File");
        final JMenuItem openPGN = new JMenuItem("Load pGN ");
        openPGN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open PGN");
            }
        });
        fileMenu.add(openPGN);
        final JMenuItem exitMenu = new JMenuItem("exit");
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenu);
        return fileMenu;
    }

    public void render() {
        this.boardPanel.render();
    }

    private class BoardPanel extends JPanel {
        public static final Dimension BOARD_DIMENSION;

        static {
            BOARD_DIMENSION = new Dimension(800, 800);
        }

        private final TilePanel[][] grid;

        public BoardPanel() throws IOException {
            super(new GridLayout(8, 8));
            this.grid = new TilePanel[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.grid[i][j] = new TilePanel(i, j);
                    this.add(this.grid[i][j]);
                }
            }
            this.setPreferredSize(BOARD_DIMENSION);
            setVisible(true);
            validate();
        }


        public void render() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.grid[i][j].render();
                }
            }
            this.setAnnotatted();
            validate();
        }

        public void setAnnotatted() {
            if (ChessGui.this.move == null) {
                return;
            }
            switch (ChessGui.this.move.getState()) {
                case Move.INVALID_MOVE:
                    break;
                case Move.INVALID_SELECTION:
                    break;
                case Move.SELECT_MOVE:
                    this.grid[ChessGui.this.move.getX1()][ChessGui.this.move.getY1()].setSelectTile();
                    break;
                case Move.NORMAL_MOVE:
                    this.grid[ChessGui.this.move.getX1()][ChessGui.this.move.getY1()].setSourceTile();
                    this.grid[ChessGui.this.move.getX2()][ChessGui.this.move.getY2()].setDestinationTile();
                    break;
                default:
                    break;
            }
            if (ChessGui.this.move.getMap() != null) {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (ChessGui.this.move.getMap()[i][j]) {
                            this.grid[i][j].setPossibleTile();
                        }
                    }
                }
            }
        }
    }

    private class TilePanel extends JPanel {
        public static final Color DESTINATION_TILE = new Color(0, 255, 0);
        public static final Color THREATEN_TILE = new Color(255, 0, 0, 255);
        private static final Dimension TILE_DIMENSION = new Dimension(10, 10);
        private static final Color DARK_SQUARE = new Color(50, 0, 20);
        private static final Color LIGHT_SQUARE = new Color(255, 255, 200);
        private static final Color SELECT_TILE = new Color(255, 255, 0);
        private static final Color SOURCE_TILE = new Color(0, 0, 255);
        private static final ImageIcon[][] IMGS = new ImageIcon[2][6];

        static {

            String path = "";
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 6; j++) {
                    try {
                        path = (i == 1 ? "white_" : "black_") + j + ".png";
                        IMGS[i][j] = new ImageIcon(ImageIO.read(new File("res/" + path)).getScaledInstance(60, 60, Image.SCALE_SMOOTH));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private final int x;
        private final int y;


        public TilePanel(int x, int y) {
            super(new GridBagLayout());
            this.x = x;
            this.y = y;
            setPreferredSize(TILE_DIMENSION);
            setBackground((this.x + this.y) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
            setVisible(true);
            addMouseListener(new Input());
            validate();
        }

        public void render() {
            removeAll();
            setBackground((this.x + this.y) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
            if (ChessGui.this.game.getPiece(this.x, this.y) != null) {
                add(new JLabel(IMGS[ChessGui.this.game.getPiece(this.x, this.y).getColor() ? 1 : 0][ChessGui.this.game.getPiece(this.x, this.y).getType()]));
            }
            revalidate();
            repaint();
        }

        public void setSourceTile() {
            this.setBackground(SOURCE_TILE);
        }

        public void setDestinationTile() {
            this.setBackground(DESTINATION_TILE);
        }

        public void setSelectTile() {
            this.setBackground(SELECT_TILE);
        }

        public void setPossibleTile() {
            this.setBackground(THREATEN_TILE);
        }

        private class Input implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("This bullshit is not supported yet you fool");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    ChessGui.this.move = ChessGui.this.game.onClick(TilePanel.this.x, TilePanel.this.y);

                    switch (ChessGui.this.move.getState()) {

                        case Move.INVALID_MOVE:

                            break;
                        case Move.SELECT_MOVE:
                            break;
                        case Move.NORMAL_MOVE:

                            break;

                        default:
                            break;
                    }
                    ChessGui.this.render();
                }

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
