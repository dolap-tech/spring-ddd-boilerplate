package com.dolap.product.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageResourceService {

    private final MessageSource messageSource;

    public MessageResourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessageOrEmpty(String messageKey, String... params) {
        return messageSource.getMessage(messageKey, params, StringUtils.EMPTY, getLocale());
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
