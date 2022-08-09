package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.ChessGame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class RightPanel extends JPanel {
    private ChessGame game;

    public RightPanel(ChessGame game) {
        super();
        setPreferredSize(new Dimension(200, 800));
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new StatusBar());
        add(new ChatPane());
        add(new Logger());
        validate();
    }


    private class StatusBar extends JPanel {
        private final JLabel status = new JLabel("Start The Game");
        private final Font font = new Font("Arial", Font.BOLD, 20);

        public StatusBar() {
            super();
            setBorder(BasicBorders.getInternalFrameBorder());
            status.setFont(font);
            status.setForeground(Color.RED);
            add(status);
            validate();
        }

        public void update() {

        }
    }

    private class Logger extends JPanel {
        private final JTextArea log = new JTextArea();

        public Logger() {
            super();
            setLayout(new BorderLayout());
            setPreferredSize(new Dimension(200, 200));
            setBorder(BasicBorders.getInternalFrameBorder());
            add(log, BorderLayout.CENTER);
            validate();
        }
    }

    private class ChatPane extends JPanel {
        private final JButton send = new JButton("Send");
        private final JScrollPane scrollPane;
        private final JPanel chat;
        private JTextField message = new JTextField();
        private int messageCount = 0;

        public ChatPane() {
            super();
            chat = new JPanel();
            //chat.setPreferredSize(new Dimension(200, 150));
            chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
            scrollPane = new JScrollPane(chat);
            scrollPane.setWheelScrollingEnabled(true);
            scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            });
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BasicBorders.getInternalFrameBorder());
            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel next = new JLabel("Message :#" + ++messageCount);
                    next.setBorder(new LineBorder(Color.BLACK, 1));
                    next.setSize(new Dimension(200, 200));
                    chat.add(next);
                    chat.validate();
                    validate();
                }
            });
            message.setSize(new Dimension(200, 10));
            add(scrollPane);
            add(message);
            add(send);
            validate();
        }
    }
}
