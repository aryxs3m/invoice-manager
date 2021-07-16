package hu.procats.invoicemanager.util;

import hu.procats.invoicemanager.models.FrontendExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class Handler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ FrontendException.class })
    public ResponseEntity<Object> handleFrontendException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                new FrontendExceptionDetails(ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

