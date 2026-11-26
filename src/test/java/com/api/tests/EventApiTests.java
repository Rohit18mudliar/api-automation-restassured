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
import com.api.utils.FakerUtils;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)

public class EventApiTests {

    private static final String IMAGE_URL = "https://softwaretester.careers/wp-content/uploads/2018/11/qa-events.png";
    private static final Logger logger = LogManager.getLogger(EventApiTests.class);

    // Builds a create-event payload with fresh random data on every call
    private CreateEventRequest randomCreateEventRequest() {
        String city = FakerUtils.randomCity();
        return new CreateEventRequest.Builder()
                .Title(FakerUtils.randomEventTitle())
                .Description(FakerUtils.randomDescription())
                .Category("Conference")
                .Venue(FakerUtils.randomVenue(city))
                .City(city)
                .EventDate(FakerUtils.randomFutureEventDate())
                .Price(FakerUtils.randomPrice())
                .TotalSeats(FakerUtils.randomSeats())
                .ImageUrl(IMAGE_URL)
                .build();
    }

    @Test(groups = "smoke",description = "Verify createEvent API with valid credentials")
    public void createEventTest() {

        logger.info("Step 1: Login and get token");
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("apiuser01@mail.com", "Rahul@123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        logger.info("Step 2: Create event");
        CreateEventRequest createEventRequest = randomCreateEventRequest();
        EventService eventService = new EventService();
        response = eventService.createEvent(loginResponse.getToken(), createEventRequest);
        CreateEventResponse createEventResponse = response.as(CreateEventResponse.class); // Deseriaize
        Assert.assertTrue(createEventResponse.getSuccess());
        Assert.assertEquals(createEventResponse.getMessage(), "Event created successfully");
        Assert.assertEquals(response.getStatusCode(), 201);
        int Id = createEventResponse.getData().getId();
        logger.info("Event created with id {}", Id);

        // Verify event details after creation
        logger.info("Step 3: Verify event {} and delete it", Id);

        response = eventService.getEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        GetEventResponse getEventResponse = response.as(GetEventResponse.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(getEventResponse.getSuccess());

        // TearDown - Delete that event
        logger.info("Step 4: Delete event {}", Id);
        response = eventService.deleteEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(description = "Verify updateEvent API with valid credentials")
    public void updateEventTest() {

        logger.info("Step 1: Login and get token");
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("apiuser01@mail.com", "Rahul@123"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        logger.info("Step 2: Create event");
        CreateEventRequest createEventRequest = randomCreateEventRequest();
        EventService eventService = new EventService();
        response = eventService.createEvent(loginResponse.getToken(), createEventRequest);
        CreateEventResponse createEventResponse = response.as(CreateEventResponse.class);// Deseriaize

        // Update event that was created, with new random values
        logger.info("Step 3: Update event");
        String updatedTitle = FakerUtils.randomEventTitle();
        String updatedCity = FakerUtils.randomCity();

        UpdateEventRequest updateEventRequest = new UpdateEventRequest
                .Builder()
                .setEventDate(FakerUtils.randomFutureEventDate())
                .setCity(updatedCity)
                .setCategory("Conference")
                .setDescription( FakerUtils.randomDescription())
                .setPrice(FakerUtils.randomPrice())
                .setTitle(updatedTitle)
                .setImageUrl(IMAGE_URL)
                .setVenue(FakerUtils.randomVenue(updatedCity))
                .setTotalSeats(FakerUtils.randomSeats())
                .build();

        response = eventService.updateEvent(loginResponse.getToken(), updateEventRequest, createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        UpdateEventResponse updateEventResponse = response.as(UpdateEventResponse.class);
        Assert.assertTrue(updateEventResponse.getSuccess());
        Assert.assertEquals(updateEventResponse.getMessage(), "Event updated successfully");
        Assert.assertEquals(updateEventResponse.getData().getTitle(), updatedTitle);
        int id = updateEventResponse.getData().getId();
        logger.info("Event id {} updated ", id);

        // TearDown - Delete that event
        logger.info("Step 4: Delete event {}", id);
        response = eventService.deleteEvent(loginResponse.getToken(), createEventResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

}
