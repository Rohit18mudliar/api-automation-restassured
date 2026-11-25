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

}
