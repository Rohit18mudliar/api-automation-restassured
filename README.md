# API Automation Framework (REST Assured + TestNG)

API test automation framework for the [EventHub practice API](https://api.eventhub.rahulshettyacademy.com), built with Java, REST Assured and TestNG. It follows a Service Object Model: tests never build HTTP requests directly, they call service classes that wrap REST Assured.

## Features

- Service Object Model: `BaseService` wraps REST Assured, and each API area has its own service class.
- Request and response POJOs, with builders for large payloads.
- Request/response logging through a REST Assured filter, with tokens, passwords and `Authorization` headers masked.
- Every log line is tagged with the running test name, for example `[EventApiTests.createEventTest]`.
- Random test data (names, emails, phone numbers, event details) through `FakerUtils`.
- TestNG listener that logs test start, result, failure summary and the suite run.
- Suite selection from the command line with `-Dsuite=<name>`.
- Nightly and on-push runs in GitHub Actions, with logs and a test report published.

## Project structure

```
ApiAutomationRestAssured/
├── pom.xml
├── testng.xml                          default suite
├── .github/workflows/maven.yml         CI pipeline
├── src/test/
│   ├── java/com/api/
│   │   ├── base/                       BaseService, AuthService, EventService, BookingService
│   │   ├── filters/                    LoggingFilter (request/response logging and masking)
│   │   ├── listeners/                  TestListener (TestNG events, test-name log tag)
│   │   ├── models/
│   │   │   ├── request/                LoginRequest, CreateEventRequest, UpdateEventRequest, CreateBookingRequest
│   │   │   └── response/               LoginResponse, event and booking responses
│   │   ├── tests/                      LoginApiTests, EventApiTests, BookingApiTests
│   │   └── utils/                      FakerUtils
│   └── resources/
│       └── log4j2.xml                  log format and appenders
├── logs/                               test.log (git-ignored)
└── target/                             build output and reports (git-ignored)
```

## How it works

```
Test  ->  Service class  ->  BaseService  ->  REST Assured  ->  LoggingFilter  ->  API
```

| Layer | Responsibility |
|---|---|
| Test class | Test steps and assertions only. No URLs, tokens or `given()` calls. |
| Service class (`AuthService`, `EventService`, `BookingService`) | One method per endpoint. Sets the token and calls a `BaseService` method. |
| `BaseService` | Base URL, builds a fresh request spec for every call, GET, POST, PUT and DELETE wrappers. |
| `LoggingFilter` | Registered globally in `BaseService`. Logs every request and response and masks secrets. |
| Models | POJOs that are serialized into request bodies and deserialized from responses with `response.as(...)`. |

A new request spec is built for every call, so headers, bodies and tokens from one request can never leak into the next.


## Tech stack

| Area | Tool | Version |
|---|---|---|
| Language | Java | 17 |
| Build | Maven | 3.x |
| API client | REST Assured | 6.3.0 |
| Test runner | TestNG | 7.12.0 |
| JSON mapping | Jackson (`tools.jackson`) | 3.3.0 |
| Logging | Log4j2 | 2.26.1 |
| Test data | Datafaker | 2.7.0 |
| Runner plugin | Maven Surefire | 3.5.2 |
| CI | GitHub Actions | |

## Prerequisites

- JDK 17 or later
- Maven 3.x on your `PATH` (check with `mvn -v`)
- Internet access, since the tests call the live API

## Running the tests

```bash
# Run the default suite (testng.xml)
mvn clean test

# Run a different suite file, e.g. smoke.xml
mvn clean test -Dsuite=smoke

# Run a single test class
mvn test -Dtest=EventApiTests

# Run a single test method
mvn test -Dtest=EventApiTests#createEventTest

# Debug output when something looks wrong
mvn clean test -X > debug.log
```

In IntelliJ, right-click `testng.xml` or a test class and choose Run. To get TestNG's own HTML reports there, enable "Use default reporters" in the TestNG run configuration.

### Suite file

`testng.xml` registers `TestListener` and runs `LoginApiTests`, `EventApiTests` and `BookingApiTests` one after another. `loginTest` is tagged with the TestNG group `smoke`, so a smoke suite can be built from it.

## Reports and logs

| Output | Location |
|---|---|
| Console and file log | `logs/test.log` |
| Surefire and TestNG reports | `target/surefire-reports/` |
| JUnit XML used by CI | `target/surefire-reports/junitreports/` |

Log lines have this shape, so you can search `test.log` for one test name to see only its traffic:

```
10:32:11.123 [main] [EventApiTests.createEventTest]  INFO ... - REQUEST  -> POST https://.../api/events
```

### What gets masked

- The `Authorization`, `Cookie`, `Set-Cookie` and `X-Api-Key` headers.
- JSON fields named `password`, `token`, `accessToken`, `refreshToken` and `secret`, in both request and response bodies.

Request bodies given as objects are converted to JSON by the filter before they are logged and masked.

## Adding a new API test

1. **Models:** add request and response POJOs under `models/request` and `models/response`.
2. **Service:** add a method to the matching service class, or create a new one that extends `BaseService`. For a new kind of input (query params, body, path id), use or add the matching `BaseService` method.
3. **Test:** write the test in a class under `tests/`, call the service, and assert on the response.
4. **Data:** use `FakerUtils` for random values.
5. **Register:** add the class to `testng.xml`. Surefire only runs what the suite file lists.
6. **Check the log:** run it once and confirm the request, response and test-name tag look right.

Conventions used in this project:

- Test classes end in `Tests`, and test methods describe the behaviour, for example `createEventTest`.
- Each test creates its own data and cleans it up (for example, deletes the event or booking it created), so tests do not depend on each other.
- Use `@Test(description = "...")` so the description appears in logs.
- Log steps with `logger.info(...)` in the test, and never log tokens or passwords.

## Continuous integration

`.github/workflows/maven.yml` runs `mvn clean test -Dsuite=testng` on Ubuntu with JDK 17.

| Trigger | When |
|---|---|
| Push and pull request | Branch `master` |
| Schedule | Daily at 20:30 UTC (2:00 AM IST) |

After the run, the workflow uploads the `logs/` folder as an artifact (kept for 6 days) and publishes the JUnit results as a "TestNG reports" check.

## Known limitations

- Tests run against a shared public practice API. Data created by other users can change results, for example the remaining seats of the event used in `BookingApiTests`.
- Test credentials are currently written in the test classes. Move them to environment variables or CI secrets before making the repository public.
- Running with TestNG `parallel` and `thread-count` is not a speedup for a suite this small, because per-thread setup costs about as much as it saves.
- Surefire runs only what `testng.xml` lists, so a new test class that is not added there will silently not run.

## Troubleshooting

| Symptom | Likely cause |
|---|---|
| `No tests to run` | The class is not listed in `testng.xml`, or the `-Dsuite` file name is wrong |
| 401 "Invalid or expired token" | The token was not set before the call, or the header was sent twice |
| Log lines show an empty `[]` | The line was logged outside a test method, such as suite start or finish |
| `release version 17 not supported` | Maven is running on an older JDK. Check `mvn -v` and `JAVA_HOME` |
| Dependencies fail to download | Check the network, then run `mvn -U clean test` |
