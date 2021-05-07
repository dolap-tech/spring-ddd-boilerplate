package com.dolap.product.manager.base;

public interface ProductManager<T, R> {

    R manage(T t);
}
