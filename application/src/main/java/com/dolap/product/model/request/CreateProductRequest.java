package com.dolap.product.model.request;

import com.dolap.product.model.request.base.ProductRequest;
import jakarta.validation.constraints.*;


public class CreateProductRequest implements ProductRequest {

    @Size(max = 50, message = "error.validation.title.not.valid")
    @NotBlank(message = "error.validation.title.not.blank")
    private String title;

    @Size(max = 200, message = "error.validation.description.not.valid")
    @NotBlank(message = "error.validation.description.not.blank")
    private String description;

    @Digits(integer = 19, fraction = 2, message = "error.validation.price.not.valid")
    @NotBlank(message = "error.validation.price.not.blank")
    private String price;

    @NotBlank(message = "error.validation.owner.id.not.blank")
    private String ownerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
