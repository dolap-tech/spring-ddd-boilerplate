package com.dolap.product.builder.request;

import com.dolap.product.model.request.CreateProductRequest;

public class CreateProductRequestBuilder {

    private String title;
    private String description;
    private String price;
    private String ownerId;

    private CreateProductRequestBuilder() {
    }

    public static CreateProductRequestBuilder aCreateRequest() {
        return new CreateProductRequestBuilder();
    }

    public CreateProductRequestBuilder title(String title) {
        this.title = title;
        return this;
    }

    public CreateProductRequestBuilder description(String description) {
        this.description = description;
        return this;
    }

    public CreateProductRequestBuilder price(String price) {
        this.price = price;
        return this;
    }

    public CreateProductRequestBuilder ownerId(String ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public CreateProductRequest build() {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setTitle(title);
        createProductRequest.setDescription(description);
        createProductRequest.setPrice(price);
        createProductRequest.setOwnerId(ownerId);
        return createProductRequest;
    }
}
