package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.util.Logable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class Logger extends JPanel implements Logable {
    private final JTextArea log = new JTextArea();
    private final JScrollPane scroll = new JScrollPane(log);

    public Logger() {
        super();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 150));
        setBorder(BasicBorders.getInternalFrameBorder());
        scroll.setWheelScrollingEnabled(true);
        scroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                e.getAdjustable().setValue(e.getAdjustable().getMaximum());
            }
        });
        add(scroll, BorderLayout.CENTER);

        validate();
    }

    @Override
    public void log(String message) {
        log.append(message + "\n");
        log.setCaretPosition(log.getDocument().getLength());
    }
}