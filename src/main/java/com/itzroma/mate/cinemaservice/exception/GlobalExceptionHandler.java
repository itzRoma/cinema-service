package com.itzroma.mate.cinemaservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itzroma.mate.cinemaservice.util.DateTimePatternUtil;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(this::getErrorMessage)
                .toList();
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(status, errors);
        return new ResponseEntity<>(httpErrorResponse, headers, status);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            return field + " " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }

    @ExceptionHandler(DataProcessingException.class)
    public ResponseEntity<Object> handleDataProcessingException(DataProcessingException ex) {
        HttpErrorResponse httpErrorResponse = new HttpErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                Collections.singletonList(ex.getMessage())
        );
        return new ResponseEntity<>(httpErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static class HttpErrorResponse {
        private final LocalDateTime timestamp;
        private final int statusCode;
        private final String statusPhrase;
        private final List<String> errors;

        private HttpErrorResponse(HttpStatus status, List<String> errors) {
            this.timestamp = LocalDateTime.now();
            this.statusCode = status.value();
            this.statusPhrase = status.getReasonPhrase();
            this.errors = errors;
        }

        @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = DateTimePatternUtil.DATE_TIME_FULL)
        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public String getStatusPhrase() {
            return statusPhrase;
        }

        public List<String> getErrors() {
            return errors;
        }
    }
}
