package com.dolap.product.rest.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

public class CorrelationIdInterceptor implements HandlerInterceptor {

    private static final String CORRELATION_ID_HEADER_NAME = "x-correlation-id";
    private static final String CORRELATION_ID_LOG_VAR_NAME = "correlationId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String correlationId = getCorrelationId(request);
        MDC.put(CORRELATION_ID_LOG_VAR_NAME, correlationId);
        return true;
    }

    private String getCorrelationId(HttpServletRequest request) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER_NAME);
        return StringUtils.isBlank(correlationId) ? UUID.randomUUID().toString() : correlationId;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        MDC.remove(CORRELATION_ID_LOG_VAR_NAME);
    }
}