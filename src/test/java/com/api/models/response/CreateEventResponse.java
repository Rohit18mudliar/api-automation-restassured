package com.api.models.response;

public class CreateEventResponse {

    private Boolean success;
    private CreateEventData data;
    private String message;

    public CreateEventResponse(Boolean success, CreateEventData data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public CreateEventResponse() {
    }

    // Getters and Setters
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public CreateEventData getData() {
        return data;
    }

    public void setData(CreateEventData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CreateEventResponse{" +
                "success=" + success +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

}
