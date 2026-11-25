package com.api.tests;

import com.api.base.AuthService;
import com.api.base.BookingService;
import com.api.models.request.CreateBookingRequest;
import com.api.models.request.LoginRequest;
import com.api.models.response.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Listeners(com.api.listeners.TestListener.class)
public class BookingApiTests {


    @Test(description = "Verify Create Booking And Search Booking List")
    public void CreateBookingAndSearchBookingListTest() {
        boolean isBookedEventPresent = false;
        // Login and get token
        AuthService authService = new AuthService();
        Response response = authService.login(new LoginRequest("jivyqyg@mailinator.com", "Pa$$w0rd!"));
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Create Booking using that token
        CreateBookingRequest createBookingRequest = new CreateBookingRequest.Builder().CustomerEmail("test@tests.com").CustomerName("Test one").CustomerPhone("+91-9876543216").Quantity(1).EventId(285).build();
        BookingService bookingService = new BookingService();
        response = bookingService.createBooking(loginResponse.getToken(), createBookingRequest);
        CreateBookingResponse createBookingResponse = response.as(CreateBookingResponse.class);
        Assert.assertEquals(createBookingResponse.getMessage(), "Booking confirmed!");
        Assert.assertEquals(response.getStatusCode(), 201);

        // Search newly created booking in the list
        Map<String, Object> queryParams = new LinkedHashMap<>();
        queryParams.put("eventId", createBookingResponse.getData().getEventId());
        queryParams.put("status", "confirmed");
        queryParams.put("page", 1);
        queryParams.put("limit", 10);
        response = bookingService.getBookings(loginResponse.getToken(), queryParams);
        Assert.assertEquals(response.getStatusCode(), 200);
        BookingListResponse bookingListResponse = response.as(BookingListResponse.class);
        List<DataItem> list = bookingListResponse.getData();
        for (DataItem data : list) {
            if (data.getBookingRef().equals(createBookingResponse.getData().getBookingRef())) {
                isBookedEventPresent = true;
                Assert.assertEquals(data.getEventId(), queryParams.get("eventId"));
                Assert.assertEquals(data.getStatus(), queryParams.get("status"));
                Assert.assertEquals(data.getCustomerName(), createBookingResponse.getData().getCustomerName());
                Assert.assertEquals(data.getCustomerPhone(), createBookingResponse.getData().getCustomerPhone());
                break;
            }
        }

        // TearDown - Delete that specific bookings
        response = bookingService.deleteBooking(loginResponse.getToken(), createBookingResponse.getData().getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        DeleteBookingResponse deleteBookingResponse = response.as(DeleteBookingResponse.class);
        Assert.assertEquals(deleteBookingResponse.getMessage(), "Booking cancelled");
        Assert.assertTrue(deleteBookingResponse.isSuccess());

    }

}
