package com.dolap.product.manager;

import com.dolap.product.converter.ProductToVOConverter;
import com.dolap.product.exception.ProductNotFoundException;
import com.dolap.product.manager.base.ProductManager;
import com.dolap.product.model.request.GetProductRequest;
import com.dolap.product.model.response.GetProductResponse;
import com.dolap.product.service.GetProductService;
import org.springframework.stereotype.Service;

@Service
public class GetProductManager implements ProductManager<GetProductRequest, GetProductResponse> {

    private final GetProductService getProductService;
    private final ProductToVOConverter productToVOConverter;

    public GetProductManager(GetProductService getProductService, ProductToVOConverter productToVOConverter) {
        this.getProductService = getProductService;
        this.productToVOConverter = productToVOConverter;
    }

    @Override
    public GetProductResponse manage(GetProductRequest getProductRequest) {
        return getProductService.get(Long.parseLong(getProductRequest.getProductId()))
                .map(productToVOConverter)
                .map(GetProductResponse::new)
                .orElseThrow(ProductNotFoundException::new);
    }
}
