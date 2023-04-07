package com.dolap.product.entity.converter;

import com.dolap.product.entity.type.ProductStatus;

import jakarta.persistence.*;

@Converter
public class ProductStatusConverter implements AttributeConverter<ProductStatus, Integer> {

    public ProductStatusConverter() {
    }

    public Integer convertToDatabaseColumn(ProductStatus status) {
        return status.getValue();
    }

    public ProductStatus convertToEntityAttribute(Integer value) {
        return ProductStatus.findByValue(value);
    }
}