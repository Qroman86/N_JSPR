package ru.netology;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Server server = new Server(64);
        server.addHandler("GET", "/messages", (request, responseStream) -> {
            // Получаем параметры запроса
            String limit = request.getQueryParam("limit");
            String offset = request.getQueryParam("offset");

            // Формируем ответ
            String responseText = "Messages requested with limit=" + limit + ", offset=" + offset;

            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/plain\r\n" +
                    "Content-Length: " + responseText.length() + "\r\n" +
                    "\r\n" +
                    responseText;

            responseStream.write(response.getBytes());
            responseStream.flush();
        });
        server.addHandler("POST", "/messages", (request, responseStream) -> {
                final var content = "messages content for POST Method".getBytes();
                responseStream.write((
                        "HTTP/1.1 200 OK\r\n" +
                                "Content-Type: " + "text/plain" + "\r\n" +
                                "Content-Length: " + content.length + "\r\n" +
                                "Connection: close\r\n" +
                                "\r\n"
                ).getBytes());
                responseStream.write(content);
                responseStream.flush();

        });
        server.listen(9999);
    }
}

