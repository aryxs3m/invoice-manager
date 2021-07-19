package hu.procats.invoicemanager.models;

public class AckResponse {
    private String message;
    private boolean success;

    public AckResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public AckResponse(String message) {
        this.message = message;
        this.success = true;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
