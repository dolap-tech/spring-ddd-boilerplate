package com.dolap.product.converter;

import com.dolap.product.converter.base.Converter;
import com.dolap.product.entity.Product;
import com.dolap.product.vo.ProductVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductToVOConverter implements Converter<Product, ProductVO> {

    @Override
    public ProductVO apply(Product product) {
        var productVO = new ProductVO();
        productVO.setTitle(product.getTitle());
        productVO.setDescription(product.getDescription());
        productVO.setPrice(new BigDecimal(product.getPrice().toString()));
        productVO.setOwnerId(product.getOwnerId());
        productVO.setStatus(product.getStatus());
        return productVO;
    }
}
