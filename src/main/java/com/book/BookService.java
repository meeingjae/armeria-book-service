package com.book;

import java.util.NoSuchElementException;
import java.util.Objects;

import com.book.annotation.BookProduceType;
import com.book.handler.NoSuchElementExceptionHandler;

import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.MediaTypeNames;
import com.linecorp.armeria.server.annotation.Blocking;
import com.linecorp.armeria.server.annotation.Consumes;
import com.linecorp.armeria.server.annotation.ConsumesJson;
import com.linecorp.armeria.server.annotation.Delete;
import com.linecorp.armeria.server.annotation.ExceptionHandler;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Order;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.Post;
import com.linecorp.armeria.server.annotation.Produces;
import com.linecorp.armeria.server.annotation.ProducesJson;
import com.linecorp.armeria.server.annotation.ResponseConverter;
import com.linecorp.armeria.server.annotation.ResponseConverters;

public class BookService {

    @Order(1)
    @Get("/books")
    @BookProduceType
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

    @Produces(MediaTypeNames.JSON)
    @Consumes(MediaTypeNames.JSON)
//    @ProducesJson
//    @ConsumesJson
    @Post("/book")
    public HttpResponse createBook(Book book) {
        return HttpResponse.ofJson(BookRepository.createUpdateBook(book));
    }

    @ResponseConverters({
            @ResponseConverter(BookResponseConverter.class),
            @ResponseConverter(BookDeletedFailedConverter.class)
    })
    @Blocking
    @Delete("/book/:id")
    public HttpResponse deleteById(@Param long id) {
        Book book = BookRepository.deleteById(id);
        return HttpResponse.ofJson(
                Objects.requireNonNullElseGet(book, () ->
                        new BookDeleteFailedResponse("failed",
                                                     "this is cause")));
    }
}
