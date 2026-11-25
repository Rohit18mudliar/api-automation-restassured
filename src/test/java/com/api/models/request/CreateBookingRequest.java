package com.api.models.request;

public class CreateBookingRequest {
    private int eventId;
    private String customerPhone;
    private int quantity;
    private String customerEmail;
    private String customerName;

    private CreateBookingRequest(Builder builder) {
        this.eventId = builder.eventId;
        this.customerPhone = builder.customerPhone;
        this.quantity = builder.quantity;
        this.customerEmail = builder.customerEmail;
        this.customerName = builder.customerName;
    }

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

    public static class Builder {
        private int eventId;
        private String customerPhone;
        private int quantity;
        private String customerEmail;
        private String customerName;

        public Builder EventId(int eventId) {
            this.eventId = eventId;
            return this;
        }

        public Builder CustomerPhone(String customerPhone) {
            this.customerPhone = customerPhone;
            return this;
        }

        public Builder Quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder CustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public Builder CustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public CreateBookingRequest build() {
            return new CreateBookingRequest(this);
        }
    }
}
