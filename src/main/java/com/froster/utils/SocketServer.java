package com.froster.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

public class SocketServer extends SocketBase {

    private final int port;


    public SocketServer(int port) {
        this.port = port;
    }

    public void start() {
        var exec = Executors.newSingleThreadExecutor();
        exec.submit(() -> {
            try (ServerSocket server = new ServerSocket(port)) {
                handleConnection(server.accept());
            } catch (IOException e) {
                handleError(e);
            }
            exec.shutdown();
        });
    }

    public static String getIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
