package com.book;

public class Book {
    private final long id;
    private final String name;
    private final long page;

    public Book(long id, String name, long page) {
        this.id = id;
        this.name = name;
        this.page = page;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPage() {
        return page;
    }
}
