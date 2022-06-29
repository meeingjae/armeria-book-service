package com.book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    static final Map<Long, Book> books;

    static {
        books = new HashMap<>();

        books.put(1L, new Book(1, "first Book", 100));
        books.put(2L, new Book(2, "second Book", 200));
        books.put(3L, new Book(3, "third Book", 300));
    }

    public static Book findById(long id) {
        return books.get(id);
    }

    public static List<Book> findAll() {
        return new ArrayList<>(books.values());
    }

    public static Book createUpdateBook(Book book) {
        return books.put(book.getId(), book);
    }

    public static Book deleteById(long id) {
        return books.remove(id);
    }
}
