package com.api.models.response;

public class CreateBookingEvent {
    private String venue;
    private String city;
    private int availableSeats;
    private String description;
    private String title;
    private int totalSeats;
    private String createdAt;
    private int price;
    private String imageUrl;
    private int id;
    private String category;
    private String eventDate;
    private String updatedAt;

    public CreateBookingEvent(String venue, String city, int availableSeats, String description, String title, int totalSeats, String createdAt, int price, String imageUrl, int id, String category, String eventDate, String updatedAt) {
        this.venue = venue;
        this.city = city;
        this.availableSeats = availableSeats;
        this.description = description;
        this.title = title;
        this.totalSeats = totalSeats;
        this.createdAt = createdAt;
        this.price = price;
        this.imageUrl = imageUrl;
        this.id = id;
        this.category = category;
        this.eventDate = eventDate;
        this.updatedAt = updatedAt;
    }

    public CreateBookingEvent() {
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getVenue() {
        return venue;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return eventDate;
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
                "Event{" +
                        "venue = '" + venue + '\'' +
                        ",city = '" + city + '\'' +
                        ",availableSeats = '" + availableSeats + '\'' +
                        ",description = '" + description + '\'' +
                        ",title = '" + title + '\'' +
                        ",totalSeats = '" + totalSeats + '\'' +
                        ",createdAt = '" + createdAt + '\'' +
                        ",price = '" + price + '\'' +
                        ",imageUrl = '" + imageUrl + '\'' +
                        ",id = '" + id + '\'' +
                        ",category = '" + category + '\'' +
                        ",eventDate = '" + eventDate + '\'' +
                        ",updatedAt = '" + updatedAt + '\'' +
                        "}";
    }
}
