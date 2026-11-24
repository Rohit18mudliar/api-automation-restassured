package com.api.tests;

import static io.restassured.RestAssured.*;   // Static import > No need to write Restassured class to access member
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class loginAPITest2 {

    @Test(description = "Verify login API with valid credentials")
    public void loginTest() {
        baseURI = "https://api.eventhub.rahulshettyacademy.com";
        RequestSpecification x = given();
        RequestSpecification y = x.header("Content-Type", " application/json");
        RequestSpecification z = y.body("{\"email\": \"student@example.com\",\"password\":\"secret123\"}");
        Response response = z.post("/api/auth/login");  //endpoint
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    public void loginTest2() {
        baseURI = "https://api.eventhub.rahulshettyacademy.com";
        Response response = given().header("Content-Type", " application/json").body("{\"email\": \"student@example.com\",\"password\":\"secret123\"}").post("/api/auth/login");
        System.out.println(response.asPrettyString());
    }

}
