package hu.procats.invoicemanager.models;

import java.util.List;

public class ValidationExceptionDetails {
    public String message;
    public List<String> errors;

    public ValidationExceptionDetails(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}
