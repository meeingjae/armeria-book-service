package com.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.internal.server.annotation.AnnotatedService;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    private static final int PORT = 8080;

    public static void main(String[] args) {
        Server server = newServer();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop().join();
            logger.info("Server has been stopped.");
        }));

        server.start().join();

        logger.info("Server has been started. Serving dummy service at http://127.0.0.1:{}",
                    server.activeLocalPort());
    }

    static Server newServer() {
        ServerBuilder sb = Server.builder();
        return sb.http(Main.PORT)
                 .annotatedService(new BookService())
                 .build();
    }
}
