package hu.procats.invoicemanager.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Exception")
public class FrontendException extends RuntimeException {
    private final String message;

    public FrontendException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}