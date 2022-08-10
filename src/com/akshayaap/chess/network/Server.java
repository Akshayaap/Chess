package com.akshayaap.chess.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket ss;
    private Socket socket;
    private int port;

    public Server(int port) throws IOException {
        this.port = port;
        this.ss = new ServerSocket(port);
    }

    public void sendMessage(String message) {

    }

    public void startConnection() throws IOException {
        this.socket = this.ss.accept();
    }
}
