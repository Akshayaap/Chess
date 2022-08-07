package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.CaptureCallBack;
import com.akshayaap.chess.game.Player;

import javax.swing.*;
import java.awt.*;

public class CaptureWindow extends JPanel implements CaptureCallBack {
    private Player player;

    public CaptureWindow(Player player) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));
        this.setPreferredSize(new Dimension(200, 200));
    }

    @Override
    public void capture(int piece) {
        
    }
}
