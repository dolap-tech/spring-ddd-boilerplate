package com.dolap.product.model.response.base;

import com.dolap.product.model.response.Status;

public abstract class ProductResponse {

    private static final Status status = Status.SUCCESS;

    protected Status getStatus() {
        return status;
    }
}
