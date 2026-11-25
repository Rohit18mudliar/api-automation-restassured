package com.api.base;

import com.api.models.request.CreateBookingRequest;
import io.restassured.response.Response;

import java.util.Map;

public class BookingService extends BaseService {

    public Response createBooking(String token, CreateBookingRequest payload) {
        setAuthToken(token);
        return postRequest(payload, "/api/bookings");

    }

    public Response getBookings(String token, Map<String, ?> queryParams) {
        setAuthToken(token);
        return getRequest("/api/bookings", queryParams);
    }

    public Response deleteBooking(String token, int id) {
        setAuthToken(token);
        return deleteRequest("/api/bookings/" + id);

    }

}