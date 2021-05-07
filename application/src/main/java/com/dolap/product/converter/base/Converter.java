package com.dolap.product.converter.base;

import java.util.function.Function;

public interface Converter<T, R> extends Function<T, R> {

    default R convert(T t) {
        return apply(t);
    }
}
