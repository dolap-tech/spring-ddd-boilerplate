package com.dolap.product.model.request;

import com.dolap.product.model.request.base.ProductRequest;
import jakarta.validation.constraints.*;

public class GetProductRequest implements ProductRequest {

    @NotBlank(message = "error.validation.product.id.not.blank")
    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "GetProductRequest{" +
                "productId='" + productId + '\'' +
                '}';
    }
}
