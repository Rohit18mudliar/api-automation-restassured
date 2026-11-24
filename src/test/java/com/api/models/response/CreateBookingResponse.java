package com.api.models.response;

public class CreateBookingResponse {
    private CreateBookingData createBookingData;
    private boolean success;
    private String message;

    public CreateBookingResponse(CreateBookingData createBookingData, boolean success, String message) {
        this.createBookingData = createBookingData;
        this.success = success;
        this.message = message;
    }

    public CreateBookingResponse() {
    }

    public void setData(CreateBookingData createBookingData) {
        this.createBookingData = createBookingData;
    }

    public CreateBookingData getData() {
        return createBookingData;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "CreateBookingResponse{" +
                        "data = '" + createBookingData + '\'' +
                        ",success = '" + success + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}
