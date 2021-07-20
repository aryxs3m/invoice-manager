package hu.procats.invoicemanager.util;

import hu.procats.invoicemanager.models.FrontendExceptionDetails;
import hu.procats.invoicemanager.models.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class Handler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ FrontendException.class })
    public ResponseEntity<Object> handleFrontendException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new FrontendExceptionDetails(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleValidationException(
            ConstraintViolationException ex, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getMessage());
        }

        return new ResponseEntity<Object>(
                new ValidationExceptionDetails("Adatbeviteli hiba.", errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST
        );
    }
}

