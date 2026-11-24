package com.api.models.response;

public class GetEventResponse {

    private boolean success;
    private EventData data;

    public GetEventResponse(boolean success, EventData data) {
        this.success = success;
        this.data = data;
    }

    public GetEventResponse() {

    }

    // Getters and Setters
    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public EventData getData() {
        return data;
    }

    public void setData(EventData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GetEventResponse{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
