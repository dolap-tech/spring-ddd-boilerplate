package com.dolap.product.rest.controller;

import com.dolap.product.builder.request.CreateProductRequestBuilder;
import com.dolap.product.entity.Product;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.model.response.CreateProductResponse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(initializers = CreateProductRestControllerIntegrationTest.class)
class CreateProductRestControllerIntegrationTest extends BaseControllerIntegrationTest {

    @Test
    void shouldCreateProduct() throws Exception {
        var title = "Cup";
        var description = "Cup Description";
        var price = BigDecimal.TEN.toString();
        var ownerId = "1";

        CreateProductRequest createProductRequest = CreateProductRequestBuilder.aCreateRequest()
                .title(title)
                .description(description)
                .price(price)
                .ownerId(ownerId)
                .build();

        MockHttpServletRequestBuilder requestBuilder = post("/product")
                .content(toJson(createProductRequest))
                .contentType(MediaType.APPLICATION_JSON);

        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Product product = (Product) entityManager.createNativeQuery("select * from product where owner_id = " + createProductRequest.getOwnerId(), Product.class).getSingleResult();

        CreateProductResponse createProductResponse = objectMapper.readValue(response.getContentAsString(), CreateProductResponse.class);

        assertThat(createProductResponse.getProductId(), equalTo(product.getId()));
        assertThat(createProductResponse.getStatus(), equalTo(Status.SUCCESS));
    }
}
