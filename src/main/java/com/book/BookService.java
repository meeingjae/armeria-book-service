package com.book;

import java.util.NoSuchElementException;

import com.book.handler.NoSuchElementExceptionHandler;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.server.annotation.Delete;
import com.linecorp.armeria.server.annotation.ExceptionHandler;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.RequestObject;

public class BookService {

    @Get("/books")
    public HttpResponse findAll() {
        return HttpResponse.ofJson(BookRepository.findAll());
    }

    @ExceptionHandler(NoSuchElementExceptionHandler.class)
    @Get("/book/:id")
    public HttpResponse findById(@Param long id) {
        Book book = BookRepository.findById(id);
        if (book == null) {
            throw new NoSuchElementException(id + " book is not found.");
        }
        return HttpResponse.ofJson(book);
    }

    @Post("/book")
    public HttpResponse createBook(@RequestObject Book book) {
        return HttpResponse.ofJson(BookRepository.createUpdateBook(book));
    }

    @ExceptionHandler(NoSuchElementExceptionHandler.class)
    @Delete("/book/:id")
    public HttpResponse deleteById(@Param long id) {
        Book book = BookRepository.deleteById(id);
        if (book == null) {
            throw new NoSuchElementException(id + " book delete failed");
        }
        return HttpResponse.ofJson(book);
    }
}
