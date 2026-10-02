package pages.admin_suite;

import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.LocatorReader;

public class ADM_001_VerifyAdminPageIsAccessible {

    private final Page page;

    // Constructor
    public ADM_001_VerifyAdminPageIsAccessible(Page page) {
        this.page = page;
    }

    // Locator Methods
    public Locator UsernameField() {
        return page.locator(LocatorReader.getLocator("loginPage.username"));
        
    }

    public Locator PasswordField() {
        return page.locator(LocatorReader.getLocator("loginPage.password"));
    }

    public Locator LoginButton() {
        return page.locator(LocatorReader.getLocator("loginPage.loginButton"));
    }

    public Locator adminTab() {
        return page.locator(LocatorReader.getLocator("adminPage.adminTab"));
    }

    public Locator adminHeading() {
        return page.locator(LocatorReader.getLocator("adminPage.adminHeading"));
    }

    // Action Methods
    // Action Methods
    public void enterUsername(String username) {
        UsernameField().fill(username);
    }

    public void enterPassword(String password) {
        PasswordField().fill(password);
    }

    public void clickLoginButton() {
        LoginButton().click();
    }
    public void clickAdminTab() {
        adminTab().click();
    }

    public void verifyAdminHeading() {
        // String expectedHeading = "Admin";
        // String actualHeading = adminHeading().textContent();
        // if (!actualHeading.equals(expectedHeading)) {
        // throw new AssertionError("Expected heading: " + expectedHeading + ", but found: " + actualHeading);
        // }

      Assert.assertEquals(adminHeading().textContent(), "Admin", "Admin heading is not displayed correctly.");

    }

}
