package org.example;

public class Main {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 7777;

        if (args.length >= 2) {
            serverAddress = args[0];
            serverPort = Integer.parseInt(args[1]);
        }

        GameClient client = new GameClient(serverAddress, serverPort);
        client.start();
    }
}