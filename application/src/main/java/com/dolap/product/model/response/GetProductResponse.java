package com.dolap.product.model.response;

import com.dolap.product.model.response.base.ProductResponse;
import com.dolap.product.vo.ProductVO;

public class GetProductResponse extends ProductResponse {

    private ProductVO product;

    public GetProductResponse() {
    }

    public GetProductResponse(ProductVO product) {
        this.product = product;
    }

    public ProductVO getProduct() {
        return product;
    }

    public void setProduct(ProductVO product) {
        this.product = product;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return "GetProductResponse{" +
                "productVO=" + product +
                '}';
    }
}
