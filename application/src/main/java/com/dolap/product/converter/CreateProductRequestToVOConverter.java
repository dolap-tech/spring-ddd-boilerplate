package com.dolap.product.converter;

import com.dolap.product.converter.base.Converter;
import com.dolap.product.model.request.CreateProductRequest;
import com.dolap.product.vo.ProductVO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CreateProductRequestToVOConverter implements Converter<CreateProductRequest, ProductVO> {

    @Override
    public ProductVO apply(CreateProductRequest createProductRequest) {
        var productVO = new ProductVO();
        productVO.setTitle(createProductRequest.getTitle());
        productVO.setDescription(createProductRequest.getDescription());
        productVO.setPrice(new BigDecimal(createProductRequest.getPrice()));
        productVO.setOwnerId(Long.parseLong(createProductRequest.getOwnerId()));
        return productVO;
    }
}
