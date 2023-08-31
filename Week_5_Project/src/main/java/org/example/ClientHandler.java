package org.example;

import org.example.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            org.example.HttpRequest request = new org.example.HttpRequest(in); // Use org.example.HttpRequest
            org.example.HttpResponse response = new org.example.HttpResponse(out); // Use org.example.HttpResponse

            RequestHandler requestHandler = new RequestHandler();
            String responseBody = requestHandler.handle(request);

            response.sendResponse("200 OK", "", responseBody); // Correct method name

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
