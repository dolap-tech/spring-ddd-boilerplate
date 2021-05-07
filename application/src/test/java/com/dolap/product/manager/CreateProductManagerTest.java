package com.dolap.product.manager;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.request.CreateProductRequestBuilder;
import com.dolap.product.builder.vo.ProductVOBuilder;
import com.dolap.product.converter.CreateProductRequestToVOConverter;
import com.dolap.product.exception.ProductCouldNotCreateException;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.model.response.CreateProductResponse;
import com.dolap.product.model.response.Status;
import com.dolap.product.service.CreateProductService;
import com.dolap.product.vo.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductManagerTest extends BaseMockitoTest {

    @Mock
    private CreateProductService createProductService;

    @Mock
    private CreateProductRequestToVOConverter createProductRequestToVOConverter;

    private CreateProductManager createProductManager;

    @BeforeEach
    void setUp() {
        createProductManager = new CreateProductManager(createProductService, createProductRequestToVOConverter);
    }

    @Test
    void shouldCreateProductWhenNotExistWaitingProductBefore() {
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

        ProductVO productVO = ProductVOBuilder.aProductVO()
                .title(title)
                .description(description)
                .price(BigDecimal.TEN)
                .ownerId(1L)
                .build();

        when(createProductRequestToVOConverter.apply(createProductRequest)).thenReturn(productVO);
        when(createProductService.existWaitingProductByOwnerId(productVO.getOwnerId())).thenReturn(false);
        when(createProductService.create(productVO)).thenReturn(1L);

        CreateProductResponse createProductResponse = createProductManager.manage(createProductRequest);

        verify(createProductRequestToVOConverter).apply(createProductRequest);
        verify(createProductService).existWaitingProductByOwnerId(productVO.getOwnerId());
        verify(createProductService).create(productVO);

        assertThat(createProductResponse.getStatus(), equalTo(Status.SUCCESS));
        assertThat(createProductResponse.getProductId(), equalTo(1L));
    }

    @Test
    void shouldThrowProductCouldNotCreateExceptionWhenExistWaitingProductBefore() {
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

        ProductVO productVO = ProductVOBuilder.aProductVO()
                .title(title)
                .description(description)
                .price(BigDecimal.TEN)
                .ownerId(1L)
                .build();

        when(createProductRequestToVOConverter.apply(createProductRequest)).thenReturn(productVO);
        when(createProductService.existWaitingProductByOwnerId(productVO.getOwnerId())).thenReturn(true);

        ProductCouldNotCreateException productCouldNotCreateException = assertThrows(
                ProductCouldNotCreateException.class,
                () -> createProductManager.manage(createProductRequest)
        );

        verify(createProductRequestToVOConverter).apply(createProductRequest);
        verify(createProductService).existWaitingProductByOwnerId(productVO.getOwnerId());

        assertThat(productCouldNotCreateException.getMessageKey(), equalTo("error.exception.product.could.not.create"));
    }
}