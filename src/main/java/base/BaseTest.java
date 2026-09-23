package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {

        // Get browser from Maven command
        // Default = chrome
        String browserName = System.getProperty("browser", "chrome");

        // Start Playwright
        playwright = Playwright.create();

        // Create browser
        browser = BrowserFactory.createBrowser(
                playwright,
                browserName);

        // Create browser context
        context = browser.newContext();

        // Create page
        page = context.newPage();

        // Get URL from config.properties
        String url = ConfigReader.getConfig("orangeHrm");

        // Navigate to application
        page.navigate(url);
    }

    @AfterMethod
    public void tearDown() {

        if (page != null) {
            page.waitForTimeout(60000);
        }

        if (page != null) {
            page.close();
        }

        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}