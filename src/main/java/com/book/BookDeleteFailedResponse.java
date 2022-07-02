package com.book;

public class BookDeleteFailedResponse {

    private final String name;
    private final String cause;

    public BookDeleteFailedResponse(String name, String cause) {
        this.name = name;
        this.cause = cause;
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }
}
