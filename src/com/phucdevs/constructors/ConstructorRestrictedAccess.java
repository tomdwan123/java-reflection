package com.phucdevs.constructors;

import com.phucdevs.web.WebServer;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorRestrictedAccess {

    public static void main(String[] args)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {

        initConfiguration();
        WebServer webServer = new WebServer();
        webServer.startServer();
    }

    private static void initConfiguration() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<ServerConfiguration> constructor =
                ServerConfiguration.class.getDeclaredConstructor(int.class, String.class);

        constructor.setAccessible(true);
        constructor.newInstance(8080, "Good Day!");
    }
}

