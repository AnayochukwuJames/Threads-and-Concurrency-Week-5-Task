package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RequestHandler {
    public String handle(HttpRequest request) throws IOException {
        String path = request.getPath();

        if ("/".equals(path)) {
            return readFromFile("/Users/decagon/Desktop/Week_5_Project/src/main/java/org/example/Files/index.html");
        } else if ("/json".equals(path)) {
            return readFromFile("/Users/decagon/Desktop/Week_5_Project/src/main/java/org/example/Files/App.json");
        } else {
            return "404 Not Found - Unknown Endpoint";
        }
    }

    private String readFromFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
}
