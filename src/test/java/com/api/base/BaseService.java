package com.api.base;

/*          Service Object Model [SOM]
            BaseService > Wrapper for Restassured
                         - Setting up Req Spec
                        - Put common items in one place for resusability
                            BaseURI ,
                            Create request - GET,PUT,POST,DELETE
                            Return response
            Service Classes to inherit from BaseService
*/

import com.api.filters.LoggingFilter;
import com.api.models.request.LoginRequest;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class BaseService {

    private static final String BASE_URL = "https://api.eventhub.rahulshettyacademy.com";
    private String authToken;   // stored, not added to a shared spec

    static { // static block to execute only once
        RestAssured.filters(new LoggingFilter());
    }

    // Builds a brand-new spec for every request
    private RequestSpecification newSpec() {
        RequestSpecification spec = given().baseUri(BASE_URL);

        if (authToken != null) {
            spec.header("Authorization", "Bearer " + authToken); // Override last token
        }
        return spec;
    }

    protected void setAuthToken(String token) {
        this.authToken = token;
    }

    protected Response postRequest(Object payload, String endpoint) {
        return newSpec().contentType(ContentType.JSON).body(payload).post(endpoint);
    }

    protected Response getRequest(String endpoint) {
        return newSpec().get(endpoint);
    }

    protected Response getRequest(String endpoint, Map<String, ?> queryParams) {
        return newSpec().contentType(ContentType.JSON).queryParams(queryParams).get(endpoint);
    }

    protected Response deleteRequest(String endpoint) {
        return newSpec().delete(endpoint);
    }

    protected Response putRequest(Object payload, String endpoint) {
        return newSpec().contentType(ContentType.JSON).body(payload).put(endpoint);
    }

}
