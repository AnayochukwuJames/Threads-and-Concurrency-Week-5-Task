package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final int PORT = 8080;

    private final ExecutorService executor;
    private final int port;

    public Main(int port, int numThreads) {
        this.port = port;
        this.executor = Executors.newFixedThreadPool(numThreads);
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                executor.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        Main httpServer = new Main(8080, 4); // Port 8080 with 4 threads
        httpServer.start();
    }
}
