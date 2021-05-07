package com.dolap.product.controller;

import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.model.request.GetProductRequest;
import com.dolap.product.model.response.CreateProductResponse;
import com.dolap.product.model.response.GetProductResponse;

public interface ProductController {

    CreateProductResponse create(CreateProductRequest request);

    GetProductResponse get(GetProductRequest request);
}
