package com.akshayaap.chess.game;

import java.io.IOException;

public interface PromotionCallback {
    public void prompt(Player player) throws IOException;
}
