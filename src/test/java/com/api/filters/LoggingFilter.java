package com.api.filters;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.regex.Pattern;

// Filter to intercept and log the request-response

public class LoggingFilter implements Filter {

    private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

    private static final Set<String> SENSITIVE_HEADERS =
            Set.of("authorization", "cookie", "set-cookie", "x-api-key");

    // Matches JSON fields like "password": "abc" and "token": "xyz"
    private static final Pattern JSON_SECRET = Pattern.compile(
            "(\"(?:password|token|accessToken|refreshToken|secret)\"\\s*:\\s*\")[^\"]*(\")",
            Pattern.CASE_INSENSITIVE);

    private static String maskBody(String body) {
        return body == null ? "" : JSON_SECRET.matcher(body).replaceAll("$1****$2");
    }

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {
        logRequest(requestSpec);
        Response response = ctx.next(requestSpec, responseSpec); // Request continues after interception to server
        logResponse(response);
        return response; // Back to Test for assertions
    }

    public void logRequest(FilterableRequestSpecification requestSpec) {
        logger.info("********** REQUEST **************");
        logger.info("REQUEST  -> {} {}", requestSpec.getMethod(), requestSpec.getURI());
        for (Header h : requestSpec.getHeaders()) {
            boolean secret = SENSITIVE_HEADERS.contains(h.getName().toLowerCase());
            logger.info("Header   : {} = {}", h.getName(), secret ? "****" : h.getValue());
        }
        Object body = requestSpec.getBody();
        if (body instanceof String) {
            logger.info("Body     : {}", maskBody((String) body));
        } else if (body != null) {
            logger.info("Body     : <{}> (object body not printed)", body.getClass().getSimpleName());
        }
        logger.info("************************");

    }

    public void logResponse(Response response) {
        logger.info("********** Response **********");
        logger.info("Response Status Code - " + response.getStatusCode());
        for (Header h : response.getHeaders()) {
            boolean secret = SENSITIVE_HEADERS.contains(h.getName().toLowerCase());
            logger.info("Header   : {} = {}", h.getName(), secret ? "****" : h.getValue());
        }
        logger.info("Response body: {}", maskBody(response.asString()));
        logger.info("****************************");
    }

}
