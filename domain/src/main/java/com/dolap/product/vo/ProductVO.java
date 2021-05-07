package com.dolap.product.vo;

import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class ProductVO {

    private String title;
    private String description;
    private BigDecimal price;
    private Long ownerId;
    private ProductStatus status = ProductStatus.WAITING;

    @JsonIgnore
    public Product getProduct() {
        var product = new Product();
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setPrice(getPrice());
        product.setOwnerId(getOwnerId());
        product.setStatus(getStatus());
        return product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProductVO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", ownerId=" + ownerId +
                ", status=" + status +
                '}';
    }
}
