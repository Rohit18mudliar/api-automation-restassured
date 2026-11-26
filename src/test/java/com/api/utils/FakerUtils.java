package com.api.utils;

import net.datafaker.Faker;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Random test data for request payloads.
 * Call these from tests so every run uses fresh values.
 */
public final class FakerUtils {

    private static final Faker FAKER = new Faker();

    private static final String[] CITIES = {"Bangalore", "Mumbai", "Delhi", "Chennai", "Hyderabad", "Pune"};

    // Date test data: 2026-12-15T09:00:00.000Z
    private static final DateTimeFormatter EVENT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneOffset.UTC);

    private FakerUtils() {
        // utility class, no instances
    }

    // ---------- Booking / customer data ----------

    /** First and last name only, e.g. "Maria Gutkowski" (no titles like "Dr." or "Miss"). */
    public static String randomName() {
        return FAKER.name().firstName() + " " + FAKER.name().lastName();
    }

    /** Email on mailinator.com so no real person's inbox is used, e.g. "maria.gutkowski@mailinator.com". */
    public static String randomEmail() {
        return FAKER.internet().username() + "@mailinator.com";
    }

    /** Indian mobile number : +91-9876543216 */
    public static String randomPhone() {
        return "+91-" + FAKER.number().numberBetween(6, 10) + FAKER.number().digits(9);
    }

    // ---------- Event data ----------

    /** e.g. "Cloud Summit 4821". Plain words and digits, so no special characters */
    public static String randomEventTitle() {
        return FAKER.options().option("Tech", "QA", "Cloud", "AI", "DevOps", "Data")
                + " " + FAKER.options().option("Summit", "Conference", "Expo", "Meetup")
                + " " + FAKER.number().numberBetween(1000, 9999);
    }

    /** A short random sentence. */
    public static String randomDescription() {
        return FAKER.lorem().sentence(8);
    }

    /** One of a fixed list of Indian cities. */
    public static String randomCity() {
        return FAKER.options().option(CITIES);
    }

    /** Venue name built from the city so both fields stay consistent, e.g. "Pune Convention Hall". */
    public static String randomVenue(String city) {
        return city + " " + FAKER.options().option("International Centre", "Convention Hall", "Expo Grounds");
    }

    /** A date 30 to 365 days from now at 09:00 UTC, in the API's format. Always in the future. */
    public static String randomFutureEventDate() {
        Instant date = Instant.now()
                .truncatedTo(ChronoUnit.DAYS)
                .plus(FAKER.number().numberBetween(30, 365), ChronoUnit.DAYS)
                .plus(9, ChronoUnit.HOURS);
        return EVENT_DATE_FORMAT.format(date);
    }

    /** A round price between 500 and 5000, in steps of 100. */
    public static int randomPrice() {
        return FAKER.number().numberBetween(5, 51) * 100;
    }

    /** Total seats between 50 and 1000. */
    public static int randomSeats() {
        return FAKER.number().numberBetween(50, 1001);
    }
}
