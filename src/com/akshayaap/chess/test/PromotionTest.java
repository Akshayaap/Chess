package com.akshayaap.chess.test;

import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.gui.Promotion;

import java.io.IOException;

public class PromotionTest {
    public static void main(String[] args) throws IOException {
        Promotion promotion = new Promotion();
        promotion.prompt(new Player(true, null));
    }
}
