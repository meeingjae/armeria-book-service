package com.book.handler;

import java.util.NoSuchElementException;

import com.linecorp.armeria.common.HttpRequest;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.ExceptionHandlerFunction;

public class NoSuchElementExceptionHandler implements ExceptionHandlerFunction {
    @Override
    public HttpResponse handleException(ServiceRequestContext ctx, HttpRequest req, Throwable cause) {
        if (cause instanceof NoSuchElementException) {
            return HttpResponse.of(HttpStatus.NOT_FOUND);
        }
        return ExceptionHandlerFunction.fallthrough();
    }
}
