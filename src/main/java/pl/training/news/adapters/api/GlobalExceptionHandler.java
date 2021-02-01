package pl.training.news.adapters.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.news.domain.NewsLoadingException;

import java.util.Locale;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice(basePackages = "pl.training.news.adapters.api")
@Log
@RequiredArgsConstructor
final class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionDto> onException(Exception exception, Locale locale) {
        log.info("Exception: " + exception);
        return createResponse(exception, INTERNAL_SERVER_ERROR, locale);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    ResponseEntity<ExceptionDto> onHttpClientErrorException(HttpClientErrorException exception, Locale locale) {
        return createResponse(exception, exception.getStatusCode(), locale);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ExceptionDto> onInvalidCountryException(IllegalArgumentException exception, Locale locale) {
        return createResponse(exception, BAD_REQUEST, locale);
    }

    @ExceptionHandler(NewsLoadingException.class)
    ResponseEntity<ExceptionDto> onNewsNotFoundException(NewsLoadingException exception, Locale locale) {
        return createResponse(exception, NOT_FOUND, locale);
    }

    private ResponseEntity<ExceptionDto> createResponse(Exception exception, HttpStatus status, Locale locale) {
        var exceptionName = exception.getClass().getSimpleName();
        String description;
        try {
            description = messageSource.getMessage(exception.getClass().getSimpleName(), null, locale);
        } catch (NoSuchMessageException ex) {
            description = exceptionName;

        }
        return ResponseEntity.status(status).body(new ExceptionDto(description));
    }

}
