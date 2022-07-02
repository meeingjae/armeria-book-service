package com.book;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.linecorp.armeria.common.HttpHeaders;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.common.ResponseHeaders;
import com.linecorp.armeria.common.annotation.Nullable;
import com.linecorp.armeria.server.ServiceRequestContext;
import com.linecorp.armeria.server.annotation.ResponseConverterFunction;

public class BookDeletedFailedConverter implements ResponseConverterFunction {

    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public HttpResponse convertResponse(ServiceRequestContext ctx, ResponseHeaders headers,
                                        @Nullable Object result, HttpHeaders trailers) throws Exception {
        if (result instanceof BookDeleteFailedResponse) {
            return HttpResponse.of(HttpStatus.CONFLICT,
                                   MediaType.JSON_UTF_8,
                                   "%s", mapper.writeValueAsString(result));
        }
        return ResponseConverterFunction.fallthrough();
    }
}
