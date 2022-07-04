package com.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linecorp.armeria.common.HttpHeaders;
import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;

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
        DocService docService = DocService
                .builder()
                // 전역
                .exampleHeaders(BookService.class,
                                HttpHeaders.of("accept","application/json"))
                //findAll
                .exampleRequests(BookService.class,
                                 "findAll")
                //findById
                .examplePaths(BookService.class,
                              "findById",
                              "/book/1")
                .exampleQueries(BookService.class,
                                "findById",
                                "search=123")
                .exampleQueries(BookService.class,
                                "findById",
                                "search=456")
                //createBook
                .exampleRequests(BookService.class,
                                 "createBook",
                                 new Book(123, "name", 256).toJson())
                .build();
        return sb.http(Main.PORT)
                 .annotatedService(new BookService())
                 .serviceUnder("/book/docs", docService)
                 .build();
    }
}
