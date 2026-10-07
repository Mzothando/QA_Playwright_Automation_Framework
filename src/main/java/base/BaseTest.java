package base;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.LoadState;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ConfigReader;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    /*
     * Stores the Playwright Page for the
     * current TestNG thread.
     *
     * This allows utilities/listeners to access
     * the current page safely.
     */
    private static final ThreadLocal<Page> PAGE =
            new ThreadLocal<>();


    @BeforeMethod
    public void setUp() {

        /*
         * =========================================
         * GET BROWSER
         * =========================================
         *
         * Browser can be supplied through Maven:
         *
         * mvn test -Dbrowser=chrome
         *
         * Default = chrome
         */

        String browserName =
                System.getProperty(
                        "browser",
                        "chrome"
                );


        /*
         * =========================================
         * START PLAYWRIGHT
         * =========================================
         */

        playwright =
                Playwright.create();


        /*
         * =========================================
         * CREATE BROWSER
         * =========================================
         */

        browser =
                BrowserFactory.createBrowser(
                        playwright,
                        browserName
                );


        /*
         * =========================================
         * CREATE BROWSER CONTEXT
         * =========================================
         */

        context =
                browser.newContext();


        /*
         * =========================================
         * CREATE PAGE
         * =========================================
         */

        page =
                context.newPage();


        /*
         * Store Page in ThreadLocal.
         *
         * This is used by:
         *
         * TestNGListener
         * HybridStepLogger
         * Other utilities
         */

        PAGE.set(page);


        /*
         * =========================================
         * DEFAULT TIMEOUT
         * =========================================
         */

        page.setDefaultTimeout(
                30000
        );


        /*
         * =========================================
         * NAVIGATION TIMEOUT
         * =========================================
         */

        page.setDefaultNavigationTimeout(
                30000
        );


        /*
         * =========================================
         * GET APPLICATION URL
         * =========================================
         */

        String url =
                ConfigReader.getConfig(
                        "orangeHrm"
                );


        /*
         * =========================================
         * NAVIGATE TO APPLICATION
         * =========================================
         */

        page.navigate(
                url
        );


        /*
         * =========================================
         * WAIT FOR PAGE TO LOAD
         * =========================================
         */

        page.waitForLoadState(
                LoadState.NETWORKIDLE,
                new Page.WaitForLoadStateOptions()
                        .setTimeout(30000)
        );
    }


    @AfterMethod
    public void tearDown() {

        try {

            /*
             * =========================================
             * CLOSE PAGE
             * =========================================
             */

            if (page != null) {

                page.close();
            }


            /*
             * =========================================
             * CLOSE CONTEXT
             * =========================================
             */

            if (context != null) {

                context.close();
            }


            /*
             * =========================================
             * CLOSE BROWSER
             * =========================================
             */

            if (browser != null) {

                browser.close();
            }


            /*
             * =========================================
             * CLOSE PLAYWRIGHT
             * =========================================
             */

            if (playwright != null) {

                playwright.close();
            }

        } finally {

            /*
             * =========================================
             * REMOVE PAGE FROM THREADLOCAL
             * =========================================
             *
             * Prevents ThreadLocal memory leaks.
             */

            PAGE.remove();
        }
    }


    /*
     * =========================================
     * GET CURRENT PAGE
     * =========================================
     */

    public static Page getPage() {

        return PAGE.get();
    }


    /*
     * =========================================
     * CHECK IF PAGE EXISTS
     * =========================================
     */

    public static boolean hasPage() {

        return PAGE.get() != null;
    }


    /*
     * =========================================
     * REMOVE CURRENT PAGE
     * =========================================
     */

    public static void removePage() {

        PAGE.remove();
    }
}