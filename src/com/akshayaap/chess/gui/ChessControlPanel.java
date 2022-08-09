package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.ChessGame;

import javax.swing.*;

public class ChessControlPanel extends JPanel {
    private final ChessGame game;
    private final JButton newGame = new JButton("New Game");
    private final JButton open = new JButton("Open");
    private final JButton save = new JButton("Save");
    private final JButton reset = new JButton("Reset");
    private final JButton undo = new JButton("Undo");
    private final JButton redo = new JButton("Redo");
    private final JButton exit = new JButton("Exit");
    private final JButton about = new JButton("About");

    public ChessControlPanel(ChessGame game) {
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(newGame);
        add(open);
        add(save);
        add(reset);
        add(undo);
        add(redo);
        add(exit);
        add(about);
        validate();
    }
}
