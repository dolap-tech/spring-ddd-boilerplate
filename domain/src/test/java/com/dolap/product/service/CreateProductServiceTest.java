package com.dolap.product.service;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.vo.ProductVOBuilder;
import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.repository.ProductRepository;
import com.dolap.product.vo.ProductVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateProductServiceTest extends BaseMockitoTest {

    @Mock
    private ProductRepository productRepository;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    private CreateProductService createProductService;

    @BeforeEach
    void setUp() {
        createProductService = new CreateProductService(productRepository);
    }

    @Test
    void shouldCreateProduct() {
        ProductVO productVO = ProductVOBuilder.aProductVO()
                .title("Cup")
                .description("Cup Description")
                .price(BigDecimal.TEN)
                .status(ProductStatus.SOLD)
                .ownerId(1L)
                .build();

        when(productRepository.save(any(Product.class))).thenAnswer(invocationOnMock -> {
            Product mockProduct = invocationOnMock.getArgument(0, Product.class);
            mockProduct.setId(1L);
            return mockProduct;
        });

        Long productId = createProductService.create(productVO);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product product = productArgumentCaptor.getValue();

        assertThat(productId, equalTo(1L));
        assertThat(product.getTitle(), equalTo(productVO.getTitle()));
        assertThat(product.getDescription(), equalTo(productVO.getDescription()));
        assertThat(product.getPrice(), equalTo(productVO.getPrice()));
        assertThat(product.getStatus(), equalTo(productVO.getStatus()));
        assertThat(product.getOwnerId(), equalTo(productVO.getOwnerId()));
    }

    @Test
    void shouldReturnTrueWhenExistProductOfOwnerBefore() {
        var ownerId = 1L;
        var productStatus = ProductStatus.WAITING;

        when(productRepository.existsByOwnerIdAndStatus(ownerId, productStatus)).thenReturn(true);

        boolean existWaitingProduct = createProductService.existWaitingProductByOwnerId(ownerId);

        verify(productRepository).existsByOwnerIdAndStatus(ownerId, productStatus);

        assertTrue(existWaitingProduct);
    }

    @Test
    void shouldReturnFalseWhenNotExistProductOfOwnerBefore() {
        var ownerId = 1L;
        var productStatus = ProductStatus.WAITING;

        when(productRepository.existsByOwnerIdAndStatus(ownerId, productStatus)).thenReturn(false);

        boolean existWaitingProduct = createProductService.existWaitingProductByOwnerId(ownerId);

        verify(productRepository).existsByOwnerIdAndStatus(ownerId, productStatus);

        assertFalse(existWaitingProduct);
    }
}