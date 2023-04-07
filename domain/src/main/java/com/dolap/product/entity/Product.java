package com.dolap.product.entity;

import com.dolap.product.entity.base.BaseEntity;
import com.dolap.product.entity.converter.ProductStatusConverter;
import com.dolap.product.entity.type.ProductStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
@SequenceGenerator(name = "id_generator", sequenceName = "seq_product", allocationSize = 1)
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Long ownerId;

    @Column(nullable = false)
    @Convert(converter = ProductStatusConverter.class)
    private ProductStatus status = ProductStatus.WAITING;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product prod = (Product) o;
        return status == prod.status &&
                title.equals(prod.title) &&
                description.equals(prod.description) &&
                price.equals(prod.price) &&
                ownerId.equals(prod.ownerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, price, ownerId, status);
    }
}
