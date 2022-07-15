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
    private Move move;

    private final BoardPanel boardPanel;

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

    public void render() throws IOException {
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

        public void movePiece(Move move) {
            this.grid[move.getX2()][move.getY2()].render();
            this.grid[move.getX1()][move.getY1()].render();
        }

        public void render() throws IOException {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.grid[i][j].render();
                }
            }
        }

        public void selectTile(Move move) {
            grid[move.getX1()][move.getY1()].setBackground(TilePanel.SELECT_TILE);
        }
    }

    private class TilePanel extends JPanel {
        private static final Dimension TILE_DIMENSION;
        private static final Color DARK_SQUARE;
        private static final Color LIGHT_SQUARE;
        private static final Color SELECT_TILE;
        private static final ImageIcon[][] IMGS;


        static {
            TILE_DIMENSION = new Dimension(10, 10);
            DARK_SQUARE = new Color(50, 0, 20);
            LIGHT_SQUARE = new Color(255, 255, 200);
            SELECT_TILE=new Color(255,0,0,200);

            IMGS = new ImageIcon[2][6];
            String path="";
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < 6; j++) {
                    try {
                        path=(i==1?"white_":"black_")+j+".png";
                        IMGS[i][j] = new ImageIcon(ImageIO.read(new File("res/" + path)).getScaledInstance(60,60, Image.SCALE_SMOOTH));
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
            if(ChessGui.this.game.getPiece(this.x,this.y)!=null){
                int i=ChessGui.this.game.getPiece(this.x,this.y).getColor()==true?1:0;
                int j=ChessGui.this.game.getPiece(this.x,this.y).getType();
            }
            validate();
        }

        public void render()  {
            setBackground((this.x + this.y) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
            removeAll();
            if(ChessGui.this.game.getPiece(this.x,this.y)!=null){
                add(new JLabel(IMGS[ChessGui.this.game.getPiece(this.x,this.y).getColor()?1:0][ChessGui.this.game.getPiece(this.x,this.y).getType()]));
            }
            validate();
        }




        private class Input implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON1) {
                    ChessGui.this.move = ChessGui.this.game.onClick(TilePanel.this.x,TilePanel.this.y);

                    switch (ChessGui.this.move.getState()) {

                        case Move.INVALID_MOVE:

                            break;
                        case Move.SELECT_MOVE:
                            ChessGui.this.boardPanel.selectTile(move);
                            break;
                        case Move.NORMAL_MOVE:
                            ChessGui.this.boardPanel.movePiece(move);
                            break;

                        default:
                            break;
                    }
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
