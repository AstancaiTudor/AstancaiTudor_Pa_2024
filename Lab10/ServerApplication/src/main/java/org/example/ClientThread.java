package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ClientThread class
 * Is responsible for handling communication with a single client.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private GameServer server;
    private PrintWriter out;
    private BufferedReader in;

    /**
     * Constructor
     * @param socket The client socket
     * @param server The server
     */
    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Error in ClientThread: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The run method is called when the thread is started.
     * It listens for messages from the client and sends a response.
     */
    @Override
    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if ("stop".equalsIgnoreCase(inputLine.trim())) {
                    server.stopServer();
                    out.println("Server stopped");
                    break;
                } else {
                    out.println("Server received the request: " + inputLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Client disconnected");
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}

