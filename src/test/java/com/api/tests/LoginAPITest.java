package com.api.tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class LoginAPITest {

    @Test(description = "Verify login API with valid credentials")
    public void loginTest() {
        RestAssured.baseURI = "https://api.eventhub.rahulshettyacademy.com";
        RequestSpecification x = RestAssured.given();
        RequestSpecification y = x.header("Content-Type", " application/json");
        RequestSpecification z = y.body("{\"email\": \"student@example.com\",\"password\":\"secret123\"}");
        Response response = z.post("/api/auth/login");
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    public void loginTest2() {
        RestAssured.baseURI = "https://api.eventhub.rahulshettyacademy.com";
        Response response = RestAssured.given().header("Content-Type", " application/json").body("{\"email\": \"student@example.com\",\"password\":\"secret123\"}").post("/api/auth/login");
        System.out.println(response.asPrettyString());
    }

}
