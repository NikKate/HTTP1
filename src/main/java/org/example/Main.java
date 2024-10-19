package org.example;

import java.io.*;

import org.example.Server;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    private static final int SERVER_PORT = 9999;
    private static final int THREAD_POOL_SIZE = 64;

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server(SERVER_PORT, THREAD_POOL_SIZE);
        server.start();

    }
}
