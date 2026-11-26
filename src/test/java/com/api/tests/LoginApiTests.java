package com.api.tests;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.api.listeners.TestListener.class)

public class LoginApiTests {
    private static final Logger logger = LogManager.getLogger(LoginApiTests.class);

    @Test(groups = "smoke",description = "Verify login API with valid credentials")
    public void loginTest() {
        logger.info("Performing Login ... ");
        LoginRequest loginRequest = new LoginRequest("apiuser01@mail.com", "Rahul@123");
        AuthService authService = new AuthService();
        Response response = authService.login(loginRequest);
        Assert.assertEquals(response.getStatusCode(),200);
        LoginResponse loginResponse = response.as(LoginResponse.class); //Deserialise
        Assert.assertTrue(loginResponse.getSuccess());
        Assert.assertNotNull(loginResponse.getToken());
        Assert.assertEquals(loginResponse.getUser().getEmail(), "apiuser01@mail.com");
        logger.info("Login successful");

    }

}
