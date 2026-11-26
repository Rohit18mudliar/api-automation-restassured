package com.api.models.request;

public class UpdateEventRequest {

    private String title;
    private String description;
    private String category;
    private String venue;
    private String city;
    private String eventDate;
    private int price;
    private int totalSeats;
    private String imageUrl;

    public UpdateEventRequest(String title, String description, String category, String venue, String city, String eventDate, int price, int totalSeats, String imageUrl) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.venue = venue;
        this.city = city;
        this.eventDate = eventDate;
        this.price = price;
        this.totalSeats = totalSeats;
        this.imageUrl = imageUrl;
    }

    private UpdateEventRequest(Builder builder) {
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

    public static Builder builder() {
        return new Builder();
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
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
        return "UpdateEventRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", venue='" + venue + '\'' +
                ", city='" + city + '\'' +
                ", eventDate='" + eventDate + '\'' +
                ", price=" + price +
                ", totalSeats=" + totalSeats +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public static class Builder {
        private String title;
        private String description;
        private String category;
        private String venue;
        private String city;
        private String eventDate;
        private int price;
        private int totalSeats;
        private String imageUrl;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setEventDate(String eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Builder setTotalSeats(int totalSeats) {
            this.totalSeats = totalSeats;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public UpdateEventRequest build() {
            return new UpdateEventRequest(this);
        }
    }
}
