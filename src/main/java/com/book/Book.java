package com.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.linecorp.armeria.server.annotation.Default;
import com.linecorp.armeria.server.annotation.Param;

public class Book {
    @Param("id")
    private long id;
    @Param("name")
    @Default("default name")
    private String name;
    @Param("page")
    private long page;

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

    public String toJson() {
        String json = "";
        try { json = new ObjectMapper().writeValueAsString(this); }
        catch (JsonProcessingException ignored) { }
        return json;
    }
}
