package com.dolap.product.entity.converter;

import com.dolap.product.entity.type.ProductStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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