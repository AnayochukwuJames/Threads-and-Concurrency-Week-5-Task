package org.example;

import java.io.PrintWriter;

public class HttpResponse {
    private final PrintWriter writer;

    public HttpResponse(PrintWriter writer) {
        this.writer = writer;
    }

    public void sendResponse(String status, String contentType, String content) {
        writer.println("HTTP/1.1 " + status);
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length());
        writer.println();
        writer.println(content);
        writer.flush();
    }
}
