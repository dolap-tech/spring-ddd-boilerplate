package com.dolap.product.rest.controller;

import com.dolap.product.builder.entity.ProductBuilder;
import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.model.response.GetProductResponse;
import com.dolap.product.model.response.Status;
import com.dolap.product.rest.controller.base.integration.BaseControllerIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(initializers = GetProductRestControllerIntegrationTest.class)
class GetProductRestControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Test
    void shouldGetProduct() throws Exception {
        String title = "Cup";
        String cupDescription = "Cup Description";
        BigDecimal price = BigDecimal.TEN;
        long ownerId = 1L;
        ProductStatus status = ProductStatus.REJECTED;

        Product product = ProductBuilder.aProduct()
                .title(title)
                .description(cupDescription)
                .price(price)
                .ownerId(ownerId)
                .status(status)
                .build();

        entityManager.persist(product);

        MockHttpServletRequestBuilder requestBuilder = get("/product?productId=" + product.getId())
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        GetProductResponse getProductResponse = objectMapper.readValue(response.getContentAsString(), GetProductResponse.class);

        assertThat(getProductResponse.getProduct().getProduct(), equalTo(product));
        assertThat(getProductResponse.getStatus(), equalTo(Status.SUCCESS));
    }
}
