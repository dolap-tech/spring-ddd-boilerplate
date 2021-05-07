package com.dolap.product.manager;

import com.dolap.product.converter.CreateProductRequestToVOConverter;
import com.dolap.product.exception.ProductCouldNotCreateException;
import com.dolap.product.manager.base.ProductManager;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.model.response.CreateProductResponse;
import com.dolap.product.service.CreateProductService;
import com.dolap.product.vo.ProductVO;
import org.springframework.stereotype.Service;

@Service
public class CreateProductManager implements ProductManager<CreateProductRequest, CreateProductResponse> {

    private final CreateProductService createProductService;
    private final CreateProductRequestToVOConverter createProductRequestToVOConverter;

    public CreateProductManager(CreateProductService createProductService, CreateProductRequestToVOConverter createProductRequestToVOConverter) {
        this.createProductService = createProductService;
        this.createProductRequestToVOConverter = createProductRequestToVOConverter;
    }

    @Override
    public CreateProductResponse manage(CreateProductRequest createProductRequest) {
        var productVO = createProductRequestToVOConverter.apply(createProductRequest);
        validate(productVO);
        final Long productId = createProductService.create(productVO);
        return new CreateProductResponse(productId);
    }

    private void validate(ProductVO productVO) {
        if (createProductService.existWaitingProductByOwnerId(productVO.getOwnerId())) {
            throw new ProductCouldNotCreateException();
        }
    }
}
