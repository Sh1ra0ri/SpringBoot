package pro.sky.skyprospringdemo.model;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;
    private final int status;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }
}