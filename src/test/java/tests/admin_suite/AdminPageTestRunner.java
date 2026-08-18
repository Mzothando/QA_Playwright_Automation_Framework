package tests.admin_suite;

import org.testng.annotations.Test;
import pages.AdminPage;

import base.BaseTest;
import pages.LoginPage;

public class AdminPageTestRunner extends BaseTest {

    // Test methods for Admin Page functionality can be added here

    @Test(priority = 1, description = "Verify Admin Tab is clickable")
    public void ADM_001_verifyAdminTabClickable() throws Exception {

        // Login to the application
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickLoginButton();

        // Verify Admin Tab is clickable
        // pages.AdminPage adminPage = new pages.AdminPage(page);
        // adminPage.clickAdminTab();

        AdminPage adminPageTest = new AdminPage(page);
        adminPageTest.clickAdminTab();
        adminPageTest.verifyAdminHeading();


    }

}
