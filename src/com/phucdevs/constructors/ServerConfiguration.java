package com.phucdevs.constructors;

import java.net.InetSocketAddress;

public class ServerConfiguration {

    private static ServerConfiguration instance;

    private final InetSocketAddress serverAddress;
    private final String greetingMessage;

    private ServerConfiguration(int port, String greetingMessage) {
        this.greetingMessage = greetingMessage;
        this.serverAddress = new InetSocketAddress("localhost", port);

        if (instance == null) {
            instance = this;
        }
    }

    public static ServerConfiguration getInstance() {
        return instance;
    }

    public InetSocketAddress getServerAddress() {
        return serverAddress;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }
}
