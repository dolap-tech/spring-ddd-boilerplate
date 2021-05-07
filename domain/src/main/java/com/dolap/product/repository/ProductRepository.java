package com.dolap.product.repository;

import com.dolap.product.entity.Product;
import com.dolap.product.entity.type.ProductStatus;
import com.dolap.product.repository.base.BaseRepository;

public interface ProductRepository extends BaseRepository<Product> {

    boolean existsByOwnerIdAndStatus(Long ownerId, ProductStatus status);
}
