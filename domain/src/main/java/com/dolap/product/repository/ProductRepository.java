package com.dolap.product.repository;

import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(Long productId);

    boolean existsByOwnerIdAndStatus(Long ownerId, ProductStatus status);
}
