package com.dolap.product.builder.request;

import com.dolap.product.model.request.GetProductRequest;

public class GetProductRequestBuilder {

    private String productId;

    private GetProductRequestBuilder() {
    }

    public static GetProductRequestBuilder aGetProductRequest() {
        return new GetProductRequestBuilder();
    }

    public GetProductRequestBuilder productId(String productId) {
        this.productId = productId;
        return this;
    }

    public GetProductRequest build() {
        var getProductRequest = new GetProductRequest();
        getProductRequest.setProductId(productId);
        return getProductRequest;
    }
}
