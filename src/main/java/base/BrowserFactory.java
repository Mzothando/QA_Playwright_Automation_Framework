package base;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public final class BrowserFactory {

    private BrowserFactory() {
        // Prevent object creation
    }

    public static Browser createBrowser(
            Playwright playwright,
            String browserName) {

        boolean headless =
                Boolean.parseBoolean(
                        System.getProperty(
                                "headless",
                                "false"
                        )
                );

        switch (browserName.toLowerCase()) {

            case "chrome":

                return playwright
                        .chromium()
                        .launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(headless)
                                        .setChannel("chrome")
                        );

            case "firefox":

                return playwright
                        .firefox()
                        .launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(headless)
                        );

            case "webkit":

                return playwright
                        .webkit()
                        .launch(
                                new BrowserType.LaunchOptions()
                                        .setHeadless(headless)
                        );

            default:

                throw new IllegalArgumentException(
                        "Unsupported browser: "
                                + browserName
                                + ". Supported browsers: "
                                + "chrome, firefox, webkit"
                );
        }
    }
}