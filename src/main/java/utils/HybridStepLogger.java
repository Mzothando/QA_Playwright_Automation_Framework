package utils;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import com.microsoft.playwright.Page;

import com.aventstack.extentreports.ExtentTest;

import io.qameta.allure.Allure;

public final class HybridStepLogger {

    /*
     * Central screenshot directory.
     *
     * Screenshots:
     *
     * target/test-artifacts/screenshots/
     */
    private static final Path SCREENSHOT_DIR =
            Paths.get(
                    "target",
                    "test-artifacts",
                    "screenshots"
            );

    private HybridStepLogger() {
        // Prevent object creation
    }

    /**
     * Takes ONE Playwright screenshot and:
     *
     * 1. Saves it physically
     * 2. Attaches it to Allure
     * 3. Displays it inside Extent Report
     */
    public static void logStepWithScreenshot(
            Page page,
            String stepName) {

        if (page == null) {

            throw new IllegalStateException(
                    "Playwright Page is null while logging step: "
                            + stepName
            );
        }

        try {

            /*
             * =========================================
             * CREATE SCREENSHOT DIRECTORY
             * =========================================
             */

            Files.createDirectories(
                    SCREENSHOT_DIR
            );

            /*
             * =========================================
             * CLEAN STEP NAME
             * =========================================
             */

            String cleanStepName =
                    stepName.replaceAll(
                            "[^a-zA-Z0-9]",
                            "_"
                    );

            /*
             * Limit filename length.
             */

            if (cleanStepName.length() > 40) {

                cleanStepName =
                        cleanStepName.substring(
                                0,
                                40
                        );
            }

            /*
             * =========================================
             * UNIQUE SCREENSHOT NAME
             * =========================================
             */

            String fileName =
                    String.format(
                            "%d_%s_%s.png",

                            System.currentTimeMillis(),

                            UUID.randomUUID()
                                    .toString()
                                    .substring(0, 8),

                            cleanStepName
                    );

            Path screenshotPath =
                    SCREENSHOT_DIR.resolve(
                            fileName
                    );

            /*
             * =========================================
             * TAKE ONE PLAYWRIGHT SCREENSHOT
             * =========================================
             */

            byte[] screenshotBytes =
                    page.screenshot(
                            new Page.ScreenshotOptions()
                                    .setFullPage(true)
                    );

            /*
             * =========================================
             * SAVE SCREENSHOT
             * =========================================
             */

            Files.write(
                    screenshotPath,
                    screenshotBytes
            );

            /*
             * =========================================
             * ALLURE ATTACHMENT
             * =========================================
             */

            Allure.attachment(
                    stepName,
                    "image/png",
                    new ByteArrayInputStream(
                            screenshotBytes
                    ),
                    null
            );

            /*
             * =========================================
             * EXTENT REPORT
             * =========================================
             */

            ExtentTest extentTest =
                    ExtentReportsManager.getTest();

            if (extentTest != null) {

                /*
                 * Extent report:
                 *
                 * target/extent-report/
                 *
                 * Screenshot:
                 *
                 * target/test-artifacts/screenshots/
                 *
                 * Therefore:
                 *
                 * ../test-artifacts/screenshots/image.png
                 */

                String relativePath =
                        "../test-artifacts/screenshots/"
                                + fileName;

                /*
                 * Add the step first.
                 */

                extentTest.info(
                        stepName
                );

                /*
                 * Add screenshot.
                 */

                extentTest.addScreenCaptureFromPath(
                        relativePath
                );

            } else {

                System.out.println(
                        "⚠️ EXTENT TEST NULL ➝ "
                                + stepName
                );
            }

            /*
             * Console output
             */

            System.out.println(
                    "📸 SCREENSHOT CAPTURED ➝ "
                            + stepName
            );

        } catch (Exception e) {

            System.err.println(
                    "❌ ERROR IN LOGGING STEP: "
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}