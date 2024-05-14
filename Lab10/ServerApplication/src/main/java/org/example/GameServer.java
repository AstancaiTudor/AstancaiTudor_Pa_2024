package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * GameServer class
 * Is responsible for starting and stopping the server, accepting client connections, and broadcasting messages to all connected clients.
 */
public class GameServer {
    private int port;
    private ServerSocket serverSocket;
    private boolean running;
    private List<ClientThread> clients;

    public GameServer(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.running = true;
    }

    /**
     * Starts the server and listens for client connections.
     * When a client connects, a new ClientThread is created and started.
     * The ClientThread is responsible for handling the communication with the client.
     */
    public void startServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (running) {
                System.out.println("Waiting for clients...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());

                ClientThread clientThread = new ClientThread(clientSocket, this);
                clients.add(clientThread);
                new Thread(clientThread).start();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stops the server.
     * Closes the server socket and disconnects all clients.
     */
    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            System.out.println("Server stopped.");
        } catch (IOException e) {
            System.err.println("Error closing the server: " + e.getMessage());
        }
    }

}
