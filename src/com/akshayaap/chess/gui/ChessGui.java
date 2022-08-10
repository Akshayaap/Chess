package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.CaptureCallBack;
import com.akshayaap.chess.game.ChessGame;
import com.akshayaap.chess.game.Move;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class ChessGui {
    private final Promotion promotionCallback = new Promotion();
    private final JFrame gameFrame;
    private final JPanel gamePanel = new JPanel();
    private final JMenuBar menuBar;
    private final BoardPanel boardPanel;
    private final ChessGame game;
    private ChessControlPanel controlPanel;
    private RightPanel rightPanel;
    private CaptureCallBack captureCallBackBlack = null;
    private CaptureCallBack captureCallBackWhite = null;
    private Move move;
    private Logger logger = new Logger();

    public ChessGui() throws IOException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.game = new ChessGame();
        captureCallBackWhite = new CaptureWindow(game.getPlayerWhite());
        captureCallBackBlack = new CaptureWindow(game.getPlayerBlack());
        game.setPromotionCallback(promotionCallback);
        game.getPlayerWhite().setCaptrueCallback(captureCallBackWhite);
        game.getPlayerBlack().setCaptrueCallback(captureCallBackBlack);
        game.setLogable(logger);
        this.gameFrame = new JFrame("A Chess Game !");
        this.gameFrame.setLayout(new FlowLayout());
        this.menuBar = new ChessMenu(game);
        this.gameFrame.setJMenuBar(this.menuBar);
        this.gameFrame.setVisible(true);
        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.boardPanel = new BoardPanel();
        this.gamePanel.setLayout(new BorderLayout());
        controlPanel = new ChessControlPanel(this);
        this.gamePanel.add(controlPanel, BorderLayout.NORTH);
        this.gamePanel.add(this.boardPanel, BorderLayout.CENTER);
        this.gamePanel.add((CaptureWindow) captureCallBackWhite, BorderLayout.WEST);
        this.gamePanel.add((CaptureWindow) captureCallBackBlack, BorderLayout.EAST);
        this.gamePanel.add(logger, BorderLayout.SOUTH);

        this.gameFrame.add(this.gamePanel, BorderLayout.CENTER);
        rightPanel = new RightPanel(game);
        controlPanel = new ChessControlPanel(this);
        this.gameFrame.add(rightPanel);

        this.gameFrame.validate();
        this.gameFrame.pack();

        update();
        this.render();
    }


    public ChessGame getGame() {
        return game;
    }

    public void render() {
        this.boardPanel.render();
        gameFrame.validate();
    }

    private void update() {
        rightPanel.update();
    }

    public void reset() {
        game.reset();
        update();
        render();
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
                case Move.ILLEGAL_MOVE:
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
        private static ImageIcon CIRCLE_GREEN;
        private static ImageIcon CIRCLE;

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

            try {
                CIRCLE = new ImageIcon(ImageIO.read(new File("res/circle.png")).getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                CIRCLE_GREEN = new ImageIcon(ImageIO.read(new File("res/circle_green.png")).getScaledInstance(60, 60, Image.SCALE_SMOOTH));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private final int x;
        private final int y;


        public TilePanel(int x, int y) {
            super();
            setLayout(new OverlayLayout(this));
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
                JLabel label = new JLabel(IMGS[ChessGui.this.game.getPiece(this.x, this.y).getColor() ? 1 : 0][ChessGui.this.game.getPiece(this.x, this.y).getType()]);
                label.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentY(CENTER_ALIGNMENT);
                add(label);
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
            JLabel label = new JLabel(CIRCLE);
            label.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentY(CENTER_ALIGNMENT);
            this.add(label);
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

                        case Move.ILLEGAL_MOVE:
                            break;
                        case Move.SELECT_MOVE:
                            break;
                        case Move.NORMAL_MOVE:

                            break;

                        default:
                            break;
                    }
                    ChessGui.this.update();
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
