package com.book.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.linecorp.armeria.server.annotation.Produces;
import com.linecorp.armeria.server.annotation.ProducesJson;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD})
@ProducesJson
@Produces("application/json") // = @ProducesJson
public @interface BookProduceType {
}
