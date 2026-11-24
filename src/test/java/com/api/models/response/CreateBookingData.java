package com.api.models.response;

public class CreateBookingData {
    private int eventId;
    private String createdAt;
    private String customerPhone;
    private int quantity;
    private int totalPrice;
    private String customerEmail;
    private int id;
    private String bookingRef;
    private CreateBookingEvent createBookingEvent;
    private String customerName;
    private String status;
    private String updatedAt;

    public CreateBookingData(int eventId, String createdAt, String customerPhone, int quantity, int totalPrice, String customerEmail, int id, String bookingRef, CreateBookingEvent createBookingEvent, String customerName, String status, String updatedAt) {
        this.eventId = eventId;
        this.createdAt = createdAt;
        this.customerPhone = customerPhone;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerEmail = customerEmail;
        this.id = id;
        this.bookingRef = bookingRef;
        this.createBookingEvent = createBookingEvent;
        this.customerName = customerName;
        this.status = status;
        this.updatedAt = updatedAt;
    }

    public CreateBookingData() {
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
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

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBookingRef(String bookingRef) {
        this.bookingRef = bookingRef;
    }

    public String getBookingRef() {
        return bookingRef;
    }

    public void setEvent(CreateBookingEvent createBookingEvent) {
        this.createBookingEvent = createBookingEvent;
    }

    public CreateBookingEvent getEvent() {
        return createBookingEvent;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return
                "Data{" +
                        "eventId = '" + eventId + '\'' +
                        ",createdAt = '" + createdAt + '\'' +
                        ",customerPhone = '" + customerPhone + '\'' +
                        ",quantity = '" + quantity + '\'' +
                        ",totalPrice = '" + totalPrice + '\'' +
                        ",customerEmail = '" + customerEmail + '\'' +
                        ",id = '" + id + '\'' +
                        ",bookingRef = '" + bookingRef + '\'' +
                        ",event = '" + createBookingEvent + '\'' +
                        ",customerName = '" + customerName + '\'' +
                        ",status = '" + status + '\'' +
                        ",updatedAt = '" + updatedAt + '\'' +
                        "}";
    }
}
