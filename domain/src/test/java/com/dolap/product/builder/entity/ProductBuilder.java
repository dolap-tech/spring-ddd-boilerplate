package com.dolap.product.builder.entity;

import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;

import java.math.BigDecimal;

public class ProductBuilder {

    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private Long ownerId;
    private ProductStatus status = ProductStatus.WAITING;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder title(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder description(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductBuilder ownerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public ProductBuilder status(ProductStatus status) {
        this.status = status;
        return this;
    }

    public Product build() {
        var product = new Product();
        product.setTitle(title);
        product.setId(id);
        product.setDescription(description);
        product.setPrice(price);
        product.setOwnerId(ownerId);
        product.setStatus(status);
        return product;
    }
}
