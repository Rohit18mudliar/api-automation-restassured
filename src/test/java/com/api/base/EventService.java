package com.api.base;

import com.api.models.request.CreateEventRequest;
import com.api.models.request.UpdateEventRequest;
import io.restassured.response.Response;

public class EventService extends BaseService {

    public Response createEvent(String token, CreateEventRequest createEventRequest) {
        setAuthToken(token);
        return postRequest(createEventRequest, "/api/events");

    }

    public Response getEvent(String token, int eventId) {
        setAuthToken(token);
        System.out.println("eventId = " + eventId);
        return getRequest("/api/events/" + eventId);

    }

    public Response deleteEvent(String token, int eventId) {
        setAuthToken(token);
        System.out.println("eventId = " + eventId);
        return deleteRequest("/api/events/" + eventId);

    }

    public Response updateEvent(String token, UpdateEventRequest updateEventRequest, int eventId) {
        setAuthToken(token);
        return putRequest(updateEventRequest, "/api/events/" + eventId);

    }

}
