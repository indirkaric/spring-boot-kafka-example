package com.indir.kafka.example.exception.error;

public class NotFoundException extends RuntimeException {
    private final RestApiError error;

    public NotFoundException(RestApiError error) {
        this.error = error;
    }

    public RestApiError getError() {
        return error;
    }
}