package com.dolap.product.service;

import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.repository.ProductRepository;
import com.dolap.product.vo.ProductVO;
import org.springframework.stereotype.Service;

@Service
public class CreateProductService {

    private final ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Long create(ProductVO productVO) {
        var product = productVO.getProduct();
        productRepository.save(product);
        return product.getId();
    }

    public boolean existWaitingProductByOwnerId(Long ownerId) {
        return productRepository.existsByOwnerIdAndStatus(ownerId, ProductStatus.WAITING);
    }
}
