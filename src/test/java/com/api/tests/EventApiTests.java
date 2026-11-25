package com.api.tests;

import com.api.base.AuthService;
import com.api.base.EventService;
import com.api.models.request.CreateEventRequest;
import com.api.models.request.LoginRequest;
import com.api.models.request.UpdateEventRequest;
import com.api.models.response.CreateEventResponse;
import com.api.models.response.GetEventResponse;
import com.api.models.response.LoginResponse;
import com.api.models.response.UpdateEventResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)

public class EventApiTests {

    @Test(description = "Verify createEvent API with valid credentials")
    public void createEventTest() {

        // Login and get token
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("apiuser01@mail.com", "Rahul@123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Create Event using that token
        CreateEventRequest createEventRequest = new CreateEventRequest.Builder().EventDate("2026-12-15T09:00:00.000Z").City("Bangalore").Category("Conference").Description("A premier technology conference.").ImageUrl("https://softwaretester.careers/wp-content/uploads/2018/11/qa-events.png").Price(1500).Title("Tech Summit 2026").TotalSeats(500).Venue("Bangalore International Centre").build();
        EventService eventService = new EventService();
        response = eventService.createEvent(loginResponse.getToken(), createEventRequest);
        CreateEventResponse createEventResponse = response.as(CreateEventResponse.class); // Deseriaize
        Assert.assertTrue(createEventResponse.getSuccess());
        Assert.assertEquals(createEventResponse.getMessage(), "Event created successfully");
        Assert.assertEquals(response.getStatusCode(), 201);

        // Verify event details after creation
        response = eventService.getEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        GetEventResponse getEventResponse = response.as(GetEventResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(getEventResponse.getSuccess());

        // TearDown - Delete that event
        response = eventService.deleteEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(description = "Verify updateEvent API with valid credentials")
    public void updateEventTest() {

        // Login and get token
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("apiuser01@mail.com", "Rahul@123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Create Event using that token
        CreateEventRequest createEventRequest = new CreateEventRequest.Builder().EventDate("2026-12-15T09:00:00.000Z").City("Bangalore").Category("Conference").Description("A premier technology conference.").ImageUrl("https://softwaretester.careers/wp-content/uploads/2018/11/qa-events.png").Price(1500).Title("Tech Summit 2026").TotalSeats(500).Venue("Bangalore International Centre").build();
        EventService eventService = new EventService();
        response = eventService.createEvent(loginResponse.getToken(), createEventRequest);
        CreateEventResponse createEventResponse = response.as(CreateEventResponse.class);// Deseriaize

        // Update event that was created
        UpdateEventRequest updateEventRequest = new UpdateEventRequest("QA Summit 2026", "A premier technology conference.", "Conference", "Bangalore International Centre", "Bangalore", "2026-12-20T09:00:00.000Z", 1000, 500, "https://softwaretester.careers/wp-content/uploads/2018/11/qa-events.png");
        response = eventService.updateEvent(loginResponse.getToken(), updateEventRequest, createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        UpdateEventResponse updateEventResponse = response.as(UpdateEventResponse.class);
        Assert.assertTrue(updateEventResponse.getSuccess());
        Assert.assertEquals(updateEventResponse.getMessage(), "Event updated successfully");
        Assert.assertEquals(updateEventResponse.getData().getTitle(), "QA Summit 2026");

    }


}
