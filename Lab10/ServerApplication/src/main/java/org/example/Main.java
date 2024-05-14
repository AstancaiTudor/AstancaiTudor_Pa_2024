package org.example;

public class Main {
    public static void main(String[] args) {
        int port = 7777;
        GameServer server = new GameServer(port);
        server.startServer();
    }
}