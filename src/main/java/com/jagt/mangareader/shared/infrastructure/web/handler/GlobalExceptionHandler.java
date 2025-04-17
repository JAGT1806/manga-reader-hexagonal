package com.jagt.mangareader.shared.infrastructure.web.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.jagt.mangareader.i18n.domain.MessageProvider;
import com.jagt.mangareader.role.domain.exceptions.RoleNotFoundException;
import com.jagt.mangareader.shared.infrastructure.web.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final MessageProvider messageProvider;

    // ----- Excepciones personalizadas
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException() {
        return buildErrorResponse(
                HttpStatus.NOT_FOUND,
                messageProvider.getMessage("error.role.not.found"),
                List.of()
        );
    }

    // ----- Excepciones -----
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException ex) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageProvider.getMessage("error.database.general"),
                List.of(getSafeDatabaseErrorMessage(ex))
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        if(cause instanceof InvalidFormatException formatException && formatException.getTargetType().isEnum()) {
            List<String> validateValues = Stream.of(formatException.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .toList();

            String fieldName = formatException.getPath() != null && !formatException.getPath().isEmpty()
                    ? formatException.getPath().getFirst().getFieldName()
                    : "desconocido";

            String message = messageProvider.getMessage("error.enum.invalid", new Object[]{fieldName, validateValues});
            return buildErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    messageProvider.getMessage("error.format.invalid"),
                    List.of(message)
            );
        }


        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                messageProvider.getMessage("error.format.invalid"),
                List.of(messageProvider.getMessage("error.format.general"))
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return buildErrorResponse(
                HttpStatus.BAD_REQUEST,
                messageProvider.getMessage("error.request.invalid"),
                List.of(ex.getMessage())
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageProvider.getMessage("error.operation.invalid"),
                List.of(ex.getMessage()));
    }

    // ----- Excepción general -----
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                messageProvider.getMessage("error.system.unexpected"),
                List.of(ex.getMessage())
        );
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String error, List<String> messages) {
        ErrorResponse response = new ErrorResponse(
                String.valueOf(status.value()),
                error,
                messages.isEmpty() ? null : messages,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, status);
    }

    private String getSafeDatabaseErrorMessage(DataAccessException e) {
        if (e.getCause() != null) {
            String message = e.getCause().getMessage();
            if(message.contains("Detail:")) {
                String detail = message.split("Detail:")[1].trim();
                int bracketIndex = detail.indexOf("]");
                if (bracketIndex != -1)
                    detail = detail.substring(0, bracketIndex).trim();

                return detail;
            }
        }
        return messageProvider.getMessage("error.database.internal");
    }
}
