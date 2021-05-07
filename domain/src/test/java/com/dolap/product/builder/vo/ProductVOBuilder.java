package com.dolap.product.builder.vo;

import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.vo.ProductVO;

import java.math.BigDecimal;

public class ProductVOBuilder {

    private String title;
    private String description;
    private BigDecimal price;
    private Long ownerId;
    private ProductStatus status;

    private ProductVOBuilder() {
    }

    public static ProductVOBuilder aProductVO() {
        return new ProductVOBuilder();
    }

    public ProductVOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ProductVOBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductVOBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductVOBuilder ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public ProductVOBuilder status(ProductStatus status) {
        this.status = status;
        return this;
    }

    public ProductVO build() {
        ProductVO productVO = new ProductVO();
        productVO.setTitle(title);
        productVO.setDescription(description);
        productVO.setPrice(price);
        productVO.setOwnerId(ownerId);
        productVO.setStatus(status);
        return productVO;
    }
}
