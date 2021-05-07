package com.dolap.product.manager;

import com.dolap.product.manager.base.ProductManager;
import com.dolap.product.model.request.base.ProductRequest;
import com.dolap.product.model.response.base.ProductResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManagerLogDecorator<T, R> implements ProductManager<T, R> {

    private final Logger logger;
    private final ProductManager<T, R> productManager;

    public static <T extends ProductRequest, R extends ProductResponse> ManagerLogDecorator<T, R> decorate(ProductManager<T, R> productManager) {
        return new ManagerLogDecorator<>(productManager);
    }

    private ManagerLogDecorator(ProductManager<T, R> productManager) {
        this.productManager = productManager;
        logger = LoggerFactory.getLogger(productManager.getClass());
    }

    @Override
    public R manage(T t) {
        putLog(t, "Request");
        R response = productManager.manage(t);
        putLog(response, "Response");
        return response;
    }

    private void putLog(Object object, String alias) {
        try {
            logger.info(alias + ": " + object.toString());
        } catch (Exception ex) {
            logger.error("An error occurred while logging " + object.getClass().getSimpleName(), ex);
        }
    }
}