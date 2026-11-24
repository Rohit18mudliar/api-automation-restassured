package com.api.models.response;

public class CreateEventData {

    private Integer id;
    private String title;
    private String description;
    private String category;
    private String venue;
    private String city;
    private String eventDate;
    private Integer price;
    private Integer totalSeats;
    private Integer availableSeats;
    private String imageUrl;
    private String createdAt;
    private String updatedAt;

    public CreateEventData(Integer id, String title, String description, String category, String venue, String city, String eventDate, Integer price, Integer totalSeats, Integer availableSeats, String imageUrl, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.venue = venue;
        this.city = city;
        this.eventDate = eventDate;
        this.price = price;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CreateEventData() {
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", venue='" + venue + '\'' +
                ", city='" + city + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", price=" + price +
                ", totalSeats=" + totalSeats +
                ", availableSeats=" + availableSeats +
                ", imageUrl='" + imageUrl + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
