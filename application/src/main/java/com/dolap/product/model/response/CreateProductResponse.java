package com.dolap.product.model.response;

import com.dolap.product.model.response.base.ProductResponse;

public class CreateProductResponse extends ProductResponse {

    private Long productId;

    public CreateProductResponse() {
    }

    public CreateProductResponse(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return "CreateProductResponse{" +
                "productId=" + productId +
                "status=" + getStatus() +
                '}';
    }


}
