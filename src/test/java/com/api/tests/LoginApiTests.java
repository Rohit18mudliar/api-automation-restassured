package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)

public class LoginApiTests {

    @Test(description = "Verify login API with valid credentials")
    public void loginTest() {
        LoginRequest loginRequest = new LoginRequest("apiuser01@mail.com", "Rahul@123");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        Assert.assertEquals(response.getStatusCode(),200);
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialise
        Assert.assertTrue(loginResponse.getSuccess());
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertEquals(loginResponse.getUser().getEmail(), "apiuser01@mail.com");
    }

}
