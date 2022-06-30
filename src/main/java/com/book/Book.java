package com.book;

import com.linecorp.armeria.server.annotation.Default;
import com.linecorp.armeria.server.annotation.Param;

public class Book {
    @Param("id")
    private final long id;
    @Param("name")
    @Default("default name")
    private final String name;
    @Param("page")
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
