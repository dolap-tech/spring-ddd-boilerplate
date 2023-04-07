package com.dolap.product.repository;

import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepositoryImpl extends BaseRepository<Product>, ProductRepository {

    boolean existsByOwnerIdAndStatus(Long ownerId, ProductStatus status);
}
