package com.dolap.product.converter;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.entity.ProductBuilder;
import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.vo.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class ProductToVOConverterTest extends BaseMockitoTest {

    private ProductToVOConverter productToVOConverter;

    @BeforeEach
    void setUp() {
        productToVOConverter = new ProductToVOConverter();
    }

    @Test
    void shouldConvertToProductVOFromProduct() {
        String title = "Cup";
        String cupDescription = "Cup Description";
        BigDecimal price = BigDecimal.TEN;
        long ownerId = 1L;

        Product product = ProductBuilder.aProduct()
                .title(title)
                .description(cupDescription)
                .price(price)
                .ownerId(ownerId)
                .status(ProductStatus.REJECTED)
                .build();

        ProductVO productVO = productToVOConverter.apply(product);

        assertThat(productVO.getTitle(), equalTo(title));
        assertThat(productVO.getDescription(), equalTo(cupDescription));
        assertThat(productVO.getPrice(), equalTo(price));
        assertThat(productVO.getOwnerId(), equalTo(ownerId));
        assertThat(productVO.getStatus(), equalTo(ProductStatus.REJECTED));
        assertThat(productVO.getProduct(), equalTo(product));
    }
}