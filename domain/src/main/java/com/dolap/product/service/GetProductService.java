package com.dolap.product.service;

import com.dolap.product.entity.Product;
import com.dolap.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetProductService {

    private final ProductRepository productRepository;

    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> get(Long productId) {
        return productRepository.findById(productId);
    }
}
