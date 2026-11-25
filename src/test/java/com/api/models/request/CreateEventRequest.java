package com.api.models.request;

public class CreateEventRequest {

    private String title;
    private String description;
    private String category;
    private String venue;
    private String city;
    private String eventDate;
    private Integer price;
    private Integer totalSeats;
    private String imageUrl;


    private CreateEventRequest(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.category = builder.category;
        this.venue = builder.venue;
        this.city = builder.city;
        this.eventDate = builder.eventDate;
        this.price = builder.price;
        this.totalSeats = builder.totalSeats;
        this.imageUrl = builder.imageUrl;
    }

    // Getters and Setters
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CreateEventRequest{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", category='" + category + '\'' + ", venue='" + venue + '\'' + ", city='" + city + '\'' + ", eventDate='" + eventDate + '\'' + ", price=" + price + ", totalSeats=" + totalSeats + ", imageUrl='" + imageUrl + '\'' + '}';
    }

    public static class Builder {

        private String title;
        private String description;
        private String category;
        private String venue;
        private String city;
        private String eventDate;
        private Integer price;
        private Integer totalSeats;
        private String imageUrl;

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Description(String description) {
            this.description = description;
            return this;
        }

        public Builder Category(String category) {
            this.category = category;
            return this;
        }

        public Builder Venue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder City(String city) {
            this.city = city;
            return this;
        }

        public Builder EventDate(String eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder Price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder TotalSeats(Integer totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public Builder ImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public CreateEventRequest build() {
            CreateEventRequest createEventRequest = new CreateEventRequest(this);
            return createEventRequest;

        }

    }

}
