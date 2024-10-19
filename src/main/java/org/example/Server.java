package org.example;

import com.sun.net.httpserver.Request;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server {
    private final int SERVER_SOCKET;
    private final List<String> validPaths = List.of("/index.html", "/spring.svg", "/spring.png",
            "/resources.html",
            "/styles.css", "/app.js", "/links.html", "/forms.html", "/classic.html", "/events.html", "/events.js");
    private final ExecutorService executorService;

    public Server(int serverSocket, int poolSize) {
        SERVER_SOCKET = serverSocket;
        executorService = Executors.newFixedThreadPool(poolSize);

    }

    public void start() throws InterruptedException {
        try (final var serverSocket = new ServerSocket(SERVER_SOCKET)) {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                executorService.execute(() -> proceedConnection(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }

    private void proceedConnection(Socket socket) {
        try (final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             final var out = new BufferedOutputStream(socket.getOutputStream())) {
            final var requestLine = in.readLine();
            final var parts = requestLine.split(" ");

            if (parts.length != 3) {

                socket.close();
                return;
            }
            String method = parts[0];
            final var path = parts[1];

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

