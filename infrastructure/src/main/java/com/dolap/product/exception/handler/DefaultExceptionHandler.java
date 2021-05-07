package com.dolap.product.exception.handler;

import com.dolap.product.exception.base.BusinessException;
import com.dolap.product.model.response.ErrorResponse;
import com.dolap.product.service.MessageResourceService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionHandler.class);
    private final MessageResourceService messageResourceService;

    public DefaultExceptionHandler(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var bindingResult = ex.getBindingResult();
        String messageKey = bindingResult.getFieldError() != null ? bindingResult.getFieldError().getDefaultMessage() : Strings.EMPTY;
        LOGGER.error("Method argument not valid for: " + messageKey, ex);
        return getErrorResponse(messageKey);
    }

    @ExceptionHandler(BusinessException.class)
    public final ResponseEntity<Object> handleAll(BusinessException ex) {
        LOGGER.error("An exception is thrown!", ex);
        return getErrorResponse(ex.getMessageKey());
    }

    private ResponseEntity<Object> getErrorResponse(String messageKey, String... params) {
        final String message = messageResourceService.getMessageOrEmpty(messageKey, params);
        var errorResponse = ErrorResponse.create(message);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
