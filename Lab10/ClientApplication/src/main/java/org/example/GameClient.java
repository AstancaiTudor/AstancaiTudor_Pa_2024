package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * GameClient class
 * Is responsible for connecting to the server and sending commands to it.
 */
public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private BufferedReader stdIn;

    /**
     * Constructor
     * Creates a new GameClient and connects to the server at the specified address and port.
     * @param serverAddress The server address
     * @param serverPort The server port
     */
    public GameClient(String serverAddress, int serverPort) {
        try {
            socket = new Socket(serverAddress, serverPort);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Connected to the server at " + serverAddress + ":" + serverPort);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + serverAddress);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + serverAddress);
            System.exit(1);
        }
    }

    /**
     * Starts the client.
     * Reads commands from the user and sends them to the server.
     * The client stops when the user types 'exit'.
     */
    public void start() {
        try {
            System.out.println("Type your commands (type 'exit' to quit):");
            String userInput;
            while ((userInput = stdIn.readLine()) != null && !userInput.equalsIgnoreCase("exit")) {
                out.println(userInput);
                System.out.println("Server says: " + in.readLine());
            }
        } catch (IOException e) {
            System.err.println("I/O error while communicating with server");
        } finally {
            try {
                out.close();
                in.close();
                stdIn.close();
                socket.close();
                System.out.println("Client stopped.");
            } catch (IOException e) {
                System.err.println("Error when closing client resources: " + e.getMessage());
            }
        }
    }

}

