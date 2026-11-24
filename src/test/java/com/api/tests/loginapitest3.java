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
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@Listeners(com.api.listeners.TestListener.class)
public class loginapitest3 {

    @Test(description = "Verify login API with valid credentials")
    public void loginTest() {
        LoginRequest loginRequest = new LoginRequest("student@example.com", "secret123");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialise -Get the body and map it to a Java object
        Assert.assertTrue(loginResponse.getSuccess());
        Assert.assertTrue(loginResponse.getToken() != null);
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertEquals(loginResponse.getUser().getId(), 23);
        Assert.assertEquals(loginResponse.getUser().getEmail(), "student@example.com");
    }

    @Test(description = "Verify createEvent API with valid credentials")
    public void createEventTest() {

        // Login and get token
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("jivyqyg@mailinator.com", "Pa$$w0rd!"));
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

        // Delete
        response = eventService.deleteEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(description = "Verify updateEvent API with valid credentials")
    public void updateEventTest() {

        // Login and get token
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("jivyqyg@mailinator.com", "Pa$$w0rd!"));
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
