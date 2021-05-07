package com.dolap.product.model.response;

public class ErrorResponse {

    private final Status status = Status.ERROR;
    private String message;

    public ErrorResponse() {
    }

    public static ErrorResponse create(String message) {
        return new ErrorResponse(message);
    }

    private ErrorResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }
}
