package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Set;

public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    // Key used in log4j2.xml as %X{test} to tag every log line with the running test
    private static final String TEST_KEY = "test";

    // e.g. "EventApiTests.createEventTest"
    private static String testLabel(ITestResult result) {
        return result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
    }

    /**
     * Invoked before running all the test methods belonging to the classes inside the test
     * tag and calling all their Configuration methods.
     *
     * @param context The test context
     */
    public void onStart(ITestContext context) {
        logger.info("*-----------------------*");
        logger.info("Test Suite started !! -" + context.getStartDate());
    }

    /**
     * Invoked after all the test methods belonging to the classes inside the test tag have
     * run and all their Configuration methods have been called.
     *
     * @param context The test context
     */
    public void onFinish(ITestContext context) {
        logger.info("*-----------------------*");
        logger.info("Test Suite completed !! " + context.getEndDate());
        logger.info("Tests count - " + context.getAllTestMethods().length);
        logger.info("Total Passed tests: {}", context.getPassedTests().getAllResults().size());
        Set<ITestResult> failed = context.getFailedTests().getAllResults();
        logger.info("Total Failed tests: {}", failed.size());
        if (!failed.isEmpty()) {
            for (ITestResult result : failed) {
                logger.error("FAILED: {}.{} - {}",
                        result.getTestClass().getName(),
                        result.getMethod().getMethodName(),
                        result.getThrowable().getMessage());
            }
        }

        Set<ITestResult> skipped = context.getSkippedTests().getAllResults();
        logger.info("Total Skipped tests: {}", skipped.size());
        if (!skipped.isEmpty()) {
            for (ITestResult result : skipped) {
                logger.error("Skipped: {}.{} - {}",
                        result.getTestClass().getName(),
                        result.getMethod().getMethodName(),
                        result.getThrowable().getMessage());
            }
        }
        logger.info("************************************************");

    }

    /**
     * Invoked each time before a test will be invoked. The ITestResult is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled ITestResult
     * @see ITestResult#STARTED
     */
    public void onTestStart(ITestResult result) {
        ThreadContext.put(TEST_KEY, testLabel(result));   // all log lines from here carry this test name
        logger.info("*--------------*");
        logger.info("Test started - " + result.getMethod().getMethodName());
        logger.info("Description - " + result.getMethod().getDescription());
    }

    /**
     * Invoked each time a test succeeds.
     *
     * @param result ITestResult containing information about the run test
     * @see ITestResult#SUCCESS
     */
    public void onTestSuccess(ITestResult result) {
        logger.info("Passed !! " + result.getMethod().getMethodName());
        ThreadContext.remove(TEST_KEY);
    }

    /**
     * Invoked each time a test fails.
     *
     * @param result ITestResult containing information about the run test
     * @see ITestResult#FAILURE
     */
    public void onTestFailure(ITestResult result) {
        logger.error("Failed !! " + result.getMethod().getMethodName());
        ThreadContext.remove(TEST_KEY);
    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result ITestResult containing information about the run test
     * @see ITestResult#SKIP
     */
    public void onTestSkipped(ITestResult result) {
        ThreadContext.put(TEST_KEY, testLabel(result));   // a skipped test may never have started
        logger.info("Skipped !! " + result.getMethod().getMethodName());
        ThreadContext.remove(TEST_KEY);
    }

}
