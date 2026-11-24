package com.api.models.request;

public class CreateBookingRequest {
    private int eventId;
    private String customerPhone;
    private int quantity;
    private String customerEmail;
    private String customerName;

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return
                "CreateBookingRequest{" +
                        "eventId = '" + eventId + '\'' +
                        ",customerPhone = '" + customerPhone + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",customerEmail = '" + customerEmail + '\'' +
                        ",customerName = '" + customerName + '\'' +
                        "}";
    }
}
