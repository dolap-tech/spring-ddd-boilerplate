package com.dolap.product.rest.controller;

import com.dolap.product.controller.ProductController;
import com.dolap.product.manager.CreateProductManager;
import com.dolap.product.manager.GetProductManager;
import com.dolap.product.manager.ManagerLogDecorator;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.model.request.GetProductRequest;
import com.dolap.product.model.response.CreateProductResponse;
import com.dolap.product.model.response.GetProductResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductRestController implements ProductController {

    private final CreateProductManager createProductManager;
    private final GetProductManager getProductManager;

    public ProductRestController(CreateProductManager createProductManager, GetProductManager getProductManager) {
        this.createProductManager = createProductManager;
        this.getProductManager = getProductManager;
    }

    @Override
    @PostMapping
    public CreateProductResponse create(@Valid @RequestBody CreateProductRequest request) {
        return ManagerLogDecorator.decorate(createProductManager).manage(request);
    }

    @Override
    @GetMapping
    public GetProductResponse get(@Valid GetProductRequest request) {
        return ManagerLogDecorator.decorate(getProductManager).manage(request);
    }
}
