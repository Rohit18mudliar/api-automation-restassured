package com.api.models.response;

public class UpdateEventResponse {
    private boolean success;
    private UpdateEventData updateEventData;
    private String message;

    // Getters and Setters
    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UpdateEventData getData() {
        return updateEventData;
    }

    public void setData(UpdateEventData updateEventData) {
        this.updateEventData = updateEventData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
