package com.dolap.product.exception.base;

public abstract class BusinessException extends RuntimeException {

    private final String messageKey;

    protected BusinessException(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
