package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.admin_suite.ADM_001_VerifyAdminPageIsAccessible;
import pages.admin_suite.ADM_002_VerifyUserManagementPageLoads;
import pages.admin_suite.ADM_003_AddUserWithValidInformation;
import pages.authentication_suite.LoginPage;

public class Admin_Suite_TestRunner extends BaseTest {

    // Test methods for Admin Page functionality can be added here

    @Test(priority = 1, description = "ADM-001 — Verify Admin page is accessible")
    public void ADM_001_VerifyAdminPageIsAccessible() throws Exception {

        // Login to the application
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickLoginButton();

        // Verify Admin Tab is clickable
        // pages.AdminPage adminPage = new pages.AdminPage(page);
        // adminPage.clickAdminTab();

        ADM_001_VerifyAdminPageIsAccessible adminPageTest = new ADM_001_VerifyAdminPageIsAccessible(page);
        adminPageTest.clickAdminTab();
        adminPageTest.verifyAdminHeading();


    }

    @Test(priority = 2, description = "ADM-002 — Verify User Management page loads")
    public void ADM_002_verifyUserManagementPageLoads() throws Exception {

        // Login to the application
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickLoginButton();

        // Verify User Management Page loads
        ADM_002_VerifyUserManagementPageLoads userManagementPageTest =  
             new ADM_002_VerifyUserManagementPageLoads(page);

        userManagementPageTest.clickAdminTab();
        userManagementPageTest.clickUserManagementDropdown();
        userManagementPageTest.clickUsersLink();
        userManagementPageTest.isUsernameFieldVisible();
        userManagementPageTest.isUserRoleFieldVisible();
        userManagementPageTest.isEmployeeNameFieldVisible();
        userManagementPageTest.isStatusFieldVisible();
        userManagementPageTest.isSearchButtonVisible();
        userManagementPageTest.isResetButtonVisible();
        userManagementPageTest.isAddUserButtonVisible();
        userManagementPageTest.isUsersRecordsTableVisible();

    }

    @Test(priority = 3, description = "ADM-003 — Add user with valid information")
    public void ADM_003_AddUserWithValidInformation() throws Exception {

        // Login to the application
        LoginPage loginPage = new LoginPage(page);
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickLoginButton();

        // Add user with valid information
        ADM_003_AddUserWithValidInformation addUserTest = new ADM_003_AddUserWithValidInformation(page);
        addUserTest.clickAdminTab();
        addUserTest.clickUserManagementDropdown();
        addUserTest.clickUsersLink();
        addUserTest.clickAddUserButton();
        addUserTest.clickUserRoleDropdown();
        addUserTest.clickUserRoleOption();
        
        

    }

}
