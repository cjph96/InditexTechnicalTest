package com.inditex.technicaltest.shared.infrastructure.ui.http.middleware;

import lombok.Getter;

@Getter
public class ErrorResponse {
    final String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(Throwable throwable) {
        this(throwable.getMessage());
    }
}
