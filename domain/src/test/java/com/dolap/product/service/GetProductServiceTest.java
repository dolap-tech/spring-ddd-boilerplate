package com.dolap.product.service;

import com.dolap.product.base.unit.BaseMockitoTest;
import com.dolap.product.builder.entity.ProductBuilder;
import com.dolap.product.entity.Product;
import com.dolap.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GetProductServiceTest extends BaseMockitoTest {

    @Mock
    private ProductRepository productRepository;

    private GetProductService getProductService;

    @BeforeEach
    void setUp() {
        getProductService = new GetProductService(productRepository);
    }

    @Test
    void shouldGetProduct() {
        var productId = 1L;

        Product product = ProductBuilder.aProduct()
                .id(productId)
                .build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> productOpt = getProductService.get(productId);

        verify(productRepository).findById(productId);

        assertTrue(productOpt.isPresent());
        assertThat(productOpt.get().getId(), equalTo(productId));
    }

    @Test
    void shouldGetEmptyProductWhenProductIdDoesNotExist() {
        var productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> productOpt = getProductService.get(productId);

        verify(productRepository).findById(productId);

        assertFalse(productOpt.isPresent());
    }
}