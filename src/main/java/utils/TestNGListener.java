package utils;

import java.lang.reflect.Field;

import com.microsoft.playwright.Page;


import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener
        implements ITestListener, IExecutionListener {

    /**
     * Runs once before the complete
     * TestNG execution starts.
     */
    @Override
    public void onExecutionStart() {

        // Initialise Extent Reports
        ExtentReportsManager.getExtentReports();

        // Create Allure environment information
        AllureEnvironmentManager.createEnvironmentFile();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "PLAYWRIGHT AUTOMATION TEST EXECUTION STARTED"
        );

        System.out.println(
                "=========================================="
        );
    }

    /**
     * Runs whenever a test method starts.
     */
    @Override
    public void onTestStart(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        String description =
                result.getMethod()
                        .getDescription();

        /*
         * Use default description
         * when @Test description is empty.
         */

        if (description == null
                || description.isBlank()) {

            description =
                    "OrangeHRM Playwright automated test";
        }

        /*
         * Create the test inside
         * Extent Reports.
         */

        ExtentReportsManager.createTest(
                testName,
                description
        );

        /*
         * Log test start.
         */

        if (ExtentReportsManager.getTest() != null) {

            ExtentReportsManager
                    .getTest()
                    .info(
                            "Test Started: "
                                    + testName
                    );
        }

        System.out.println(
                "TEST STARTED: "
                        + testName
        );
    }

    /**
     * Runs when a test PASSES.
     */
    @Override
    public void onTestSuccess(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        /*
         * Get Playwright Page.
         */

        Page page =
                getPage(result);

        /*
         * Capture final PASS screenshot.
         *
         * HybridStepLogger will:
         *
         * 1. Take ONE screenshot
         * 2. Save screenshot physically
         * 3. Attach screenshot to Allure
         * 4. Display screenshot in Extent
         */

        if (page != null) {

            HybridStepLogger
                    .logStepWithScreenshot(
                            page,
                            testName
                                    + " - PASS Evidence"
                    );
        }

        /*
         * Mark test as PASSED
         * inside Extent Report.
         */

        if (ExtentReportsManager.getTest() != null) {

            ExtentReportsManager
                    .getTest()
                    .pass(
                            "✅ Test Passed"
                    );
        }

        System.out.println(
                "TEST PASSED: "
                        + testName
        );

        /*
         * Remove ExtentTest from
         * current ThreadLocal.
         */

        ExtentReportsManager.removeTest();
    }

    /**
     * Runs when a test FAILS.
     */
    @Override
    public void onTestFailure(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        Throwable throwable =
                result.getThrowable();

        /*
         * Get Playwright Page.
         */

        Page page =
                getPage(result);

        /*
         * Capture FAILURE screenshot
         * before Playwright is closed.
         */

        if (page != null) {

            HybridStepLogger
                    .logStepWithScreenshot(
                            page,
                            testName
                                    + " - FAILURE Evidence"
                    );
        }

        /*
         * Add exception / stack trace
         * to Extent Report.
         */

        if (ExtentReportsManager.getTest() != null) {

            if (throwable != null) {

                ExtentReportsManager
                        .getTest()
                        .fail(throwable);

            } else {

                ExtentReportsManager
                        .getTest()
                        .fail(
                                "❌ Test Failed"
                        );
            }
        }

        System.err.println(
                "TEST FAILED: "
                        + testName
        );

        /*
         * Remove ExtentTest from
         * current ThreadLocal.
         */

        ExtentReportsManager.removeTest();
    }

    /**
     * Runs when a test is SKIPPED.
     */
    @Override
    public void onTestSkipped(
            ITestResult result) {

        String testName =
                result.getMethod()
                        .getMethodName();

        Throwable throwable =
                result.getThrowable();

        /*
         * Get Playwright Page.
         */

        Page page =
                getPage(result);

        /*
         * Capture screenshot if
         * Playwright Page is available.
         */

        if (page != null) {

            HybridStepLogger
                    .logStepWithScreenshot(
                            page,
                            testName
                                    + " - SKIPPED Evidence"
                    );
        }

        /*
         * Mark test as skipped
         * in Extent Reports.
         */

        if (ExtentReportsManager.getTest() != null) {

            ExtentReportsManager
                    .getTest()
                    .skip(
                            "⚠️ Test Skipped"
                    );

            /*
             * Add reason / exception
             * when available.
             */

            if (throwable != null) {

                ExtentReportsManager
                        .getTest()
                        .skip(throwable);
            }
        }

        System.out.println(
                "TEST SKIPPED: "
                        + testName
        );

        /*
         * Remove ExtentTest from
         * current ThreadLocal.
         */

        ExtentReportsManager.removeTest();
    }

    /**
     * Runs once after the complete
     * TestNG execution finishes.
     */
    @Override
    public void onExecutionFinish() {

        /*
         * Write all Extent test information
         * into the HTML report.
         */

        ExtentReportsManager.flushReport();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "PLAYWRIGHT AUTOMATION TEST EXECUTION COMPLETED"
        );

        System.out.println(
                "=========================================="
        );

        /*
         * Extent Report location.
         */

        System.out.println(
                "Extent Report:"
        );

        System.out.println(
                System.getProperty("user.dir")
                        + "/target/extent-report/"
                        + "Extent_EvidenceReport.html"
        );

        /*
         * Allure Results location.
         */

        System.out.println(
                "Allure Results:"
        );

        System.out.println(
                System.getProperty("user.dir")
                        + "/target/allure-report/"
                        + "allure-results"
        );

        System.out.println(
                "=========================================="
        );
    }

    /**
     * Gets the Playwright Page from the
     * current TestNG test instance.
     *
     * BaseTest contains:
     *
     * protected Page page;
     *
     * Reflection is used because page is
     * protected inside BaseTest.
     */
    private Page getPage(
            ITestResult result) {

        Object testInstance =
                result.getInstance();

        if (testInstance == null) {

            return null;
        }

        try {

            Class<?> currentClass =
                    testInstance.getClass();

            /*
             * Search current class and
             * parent classes.
             */

            while (currentClass != null) {

                try {

                    Field pageField =
                            currentClass.getDeclaredField(
                                    "page"
                            );

                    pageField.setAccessible(true);

                    Object pageObject =
                            pageField.get(
                                    testInstance
                            );

                    if (pageObject instanceof Page) {

                        return (Page) pageObject;
                    }

                    return null;

                } catch (NoSuchFieldException e) {

                    currentClass =
                            currentClass.getSuperclass();
                }
            }

        } catch (Exception e) {

            System.err.println(
                    "Unable to retrieve Playwright Page: "
                            + e.getMessage()
            );
        }

        return null;
    }
}