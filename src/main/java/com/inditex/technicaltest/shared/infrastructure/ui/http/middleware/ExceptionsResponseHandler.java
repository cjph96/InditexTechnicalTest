package com.inditex.technicaltest.shared.infrastructure.ui.http.middleware;

import com.inditex.technicaltest.shared.domain.DomainValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseBody
public class ExceptionsResponseHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> throwable(Throwable throwable) {
        return ResponseEntity.internalServerError().body(new ErrorResponse("Internal Server Error"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> illegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception));
    }

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ErrorResponse> domainValidationException(DomainValidationException exception) {
        return ResponseEntity.unprocessableEntity().body(new ErrorResponse(exception));
    }
}
