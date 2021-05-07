package com.dolap.product.converter;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.request.CreateProductRequestBuilder;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.vo.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

class CreateProductRequestToVOConverterTest extends BaseMockitoTest {

    private CreateProductRequestToVOConverter createProductRequestToVOConverter;

    @BeforeEach
    void setUp() {
        createProductRequestToVOConverter = new CreateProductRequestToVOConverter();
    }

    @Test
    void shouldConvertProductVOFromCreateProductRequest() {
        String title = "Cup";
        String description = "Cup Description";
        String price = BigDecimal.TEN.toString();
        String ownerId = "1";

        CreateProductRequest createProductRequest = CreateProductRequestBuilder.aCreateRequest()
                .title(title)
                .description(description)
                .price(price)
                .ownerId(ownerId)
                .build();

        ProductVO productVO = createProductRequestToVOConverter.apply(createProductRequest);

        assertThat(productVO.getTitle(), equalTo(title));
        assertThat(productVO.getDescription(), equalTo(description));
        assertThat(productVO.getPrice(), equalTo(BigDecimal.TEN));
        assertThat(productVO.getOwnerId(), equalTo(1L));
    }
}