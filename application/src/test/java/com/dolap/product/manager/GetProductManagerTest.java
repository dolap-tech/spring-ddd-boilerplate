package com.dolap.product.manager;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.entity.ProductBuilder;
import com.dolap.product.builder.request.GetProductRequestBuilder;
import com.dolap.product.builder.vo.ProductVOBuilder;
import com.dolap.product.converter.ProductToVOConverter;
import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.exception.ProductNotFoundException;
import com.dolap.product.model.request.GetProductRequest;
import com.dolap.product.model.response.GetProductResponse;
import com.dolap.product.model.response.Status;
import com.dolap.product.service.GetProductService;
import com.dolap.product.vo.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetProductManagerTest extends BaseMockitoTest {

    @Mock
    private GetProductService getProductService;

    @Mock
    private ProductToVOConverter productToVOConverter;

    private GetProductManager getProductManager;

    @BeforeEach
    void setUp() {
        getProductManager = new GetProductManager(getProductService, productToVOConverter);
    }

    @Test
    void shouldGetProduct() {
        String title = "Cup";
        String description = "Cup Description";
        ProductStatus status = ProductStatus.APPROVED;
        BigDecimal price = BigDecimal.TEN;
        long ownerId = 2L;

        GetProductRequest getProductRequest = GetProductRequestBuilder.aGetProductRequest()
                .productId("1")
                .build();

        Product product = ProductBuilder.aProduct()
                .id(1L)
                .title(title)
                .description(description)
                .status(status)
                .price(price)
                .ownerId(ownerId)
                .build();

        ProductVO productVO = ProductVOBuilder.aProductVO()
                .title(title)
                .description(description)
                .status(ProductStatus.APPROVED)
                .price(price)
                .ownerId(ownerId)
                .build();

        when(getProductService.get(1L)).thenReturn(Optional.of(product));
        when(productToVOConverter.apply(product)).thenReturn(productVO);

        GetProductResponse getProductResponse = getProductManager.manage(getProductRequest);

        verify(getProductService).get(1L);
        verify(productToVOConverter).apply(product);

        assertThat(getProductResponse.getProduct(), equalTo(productVO));
        assertThat(getProductResponse.getStatus(), equalTo(Status.SUCCESS));
    }

    @Test
    void shouldThrowProductNotFoundExceptionWhenProductNotExist() {
        GetProductRequest getProductRequest = GetProductRequestBuilder.aGetProductRequest()
                .productId("1")
                .build();

        when(getProductService.get(1L)).thenReturn(Optional.empty());

        ProductNotFoundException productNotFoundException = assertThrows(
                ProductNotFoundException.class,
                () -> getProductManager.manage(getProductRequest)
        );

        verify(getProductService).get(1L);

        assertThat(productNotFoundException.getMessageKey(), equalTo("error.exception.product.not.found"));
    }
}