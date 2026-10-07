package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.admin_suite.ADM_001_VerifyAdminPageIsAccessible;
import pages.admin_suite.ADM_002_VerifyUserManagementPageLoads; 
import pages.admin_suite.ADM_003_AddUserWithValidInformation;
import utils.ConfigReader;
import utils.ExcelReader;
import utils.HybridStepLogger;

@Listeners({utils.TestNGListener.class})
public class Admin_Suite_TestRunner extends BaseTest {

    // ================ DATA PROVIDERS ================

    // == Data Providers for test 1 ==
    @DataProvider(name = "ADM_001_VerifyAdminPageIsAccessibleData")
    public Object[][] ADM_001_VerifyAdminPageIsAccessibleData() throws Exception{
        return ExcelReader.getExcelData(
            ConfigReader.getConfig("authentication.workbook"),
            ConfigReader.getConfig("authentication.ValidLoginCreds.Sheet")

        );
            
    }

    // == Data Providers 2 ==
    @DataProvider(name = "ADM_002_verifyUserManagementPageLoads")
    public Object[][] ADM_002_verifyUserManagementPageLoadsData() throws Exception{
        return ExcelReader.getExcelData(
            ConfigReader.getConfig("authentication.workbook"),
            ConfigReader.getConfig("authentication.ValidLoginCreds.Sheet")

        );
            
    }

    // Data provider for test case3
    @DataProvider(name = "CombinedValidCredsAndValidUserData")
    public Object[][] CombinedValidCredsAndValidUserData() throws Exception {

        // Read login credentials from Login-Suite.xlsx
        String[][] validLoginData = utils.ExcelReader.getExcelData(
                utils.ConfigReader.getConfig(
                        "authentication.workbook"
                ),
                utils.ConfigReader.getConfig(
                        "authentication.ValidLoginCreds.Sheet"
                )
        );

        // Read user data from Admin-Suite.xlsx
        String[][] validUserData = utils.ExcelReader.getExcelData(
                utils.ConfigReader.getConfig(
                        "admin.workbook"
                ),
                utils.ConfigReader.getConfig(
                        "admin.createUserRole.Sheet"
                )
        );

        // Make sure login data exists
        if (validLoginData.length == 0) {
            throw new RuntimeException(
                    "No valid login data found in Login-Suite.xlsx"
            );
        }

        // Make sure admin user data exists
        if (validUserData.length == 0) {
            throw new RuntimeException(
                    "No user data found in Admin-Suite.xlsx"
            );
        }

        /*
        * Login-Suite.xlsx:
        * username | password
        *
        * Admin-Suite.xlsx:
        * userRole | employeeNameHint | status |
        * passwordValue | confirmPassword
        *
        * Combined:
        * username | password | userRole | employeeNameHint |
        * status | passwordValue | confirmPassword
        */

        Object[][] combinedData =
                new Object[validUserData.length][8];

        for (int i = 0; i < validUserData.length; i++) {

            // Login-Suite.xlsx
            combinedData[i][0] = validLoginData[0][0];
            combinedData[i][1] = validLoginData[0][1];

            // Admin-Suite.xlsx
            combinedData[i][2] = validUserData[i][0];
            combinedData[i][3] = validUserData[i][1];
            combinedData[i][4] = validUserData[i][2];
            combinedData[i][5] = validUserData[i][3];
            combinedData[i][6] = validUserData[i][4];
            combinedData[i][7] = validUserData[i][5];
        }

        return combinedData;
    }
    
    


    // ================== TEST CASES ======================


    // ================= Test Case 1 ======================
    @Test(priority = 1, dataProvider = "ADM_001_VerifyAdminPageIsAccessibleData", description = "ADM-001 — Verify Admin page is accessible")
    public void ADM_001_VerifyAdminPageIsAccessible(String username, String password) throws Exception {

        ADM_001_VerifyAdminPageIsAccessible adminPageTest = new ADM_001_VerifyAdminPageIsAccessible(page);
        
        adminPageTest.enterUsername(username);
        adminPageTest.enterPassword(password);

        HybridStepLogger.logStepWithScreenshot(
                page,"Step 1: Entered valid username and password"
        );

        adminPageTest.clickLoginButton();  
         
        HybridStepLogger.logStepWithScreenshot(
                page,"Step 2: Clicked login button"
        );

        adminPageTest.clickAdminTab();
        adminPageTest.verifyAdminHeading();




    }

    // ================= Test Case 2 ======================
    @Test(priority = 2, dataProvider = "ADM_001_VerifyAdminPageIsAccessibleData", description = "ADM-002 — Verify User Management page loads")
    public void ADM_002_verifyUserManagementPageLoads(String username, String password) throws Exception {

       
        ADM_002_VerifyUserManagementPageLoads userManagementPageTest =  
             new ADM_002_VerifyUserManagementPageLoads(page);

        userManagementPageTest.enterUsername(username);
        userManagementPageTest.enterPassword(password);
        userManagementPageTest.clickLoginButton();  
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

    // ================= Test Case 3 ======================
    @Test(priority = 3, dataProvider = "CombinedValidCredsAndValidUserData", description = "ADM-003 — Add user with valid information")
    public void ADM_003_AddUserWithValidInformation(String username, String password, String userRole, String employeeName, 
        String status, String newUsername, String userPassword, String confirmPassword) throws Exception {

        ADM_003_AddUserWithValidInformation addUserTest = new ADM_003_AddUserWithValidInformation(page);

        addUserTest.enterUsername(username);
        addUserTest.enterPassword(password);
        addUserTest.clickLoginButton();
        addUserTest.clickAdminTab();
        addUserTest.clickUserManagementDropdown();
        addUserTest.clickUsersLink();
        addUserTest.clickAddUserButton();
        addUserTest.clickUserRoleDropdown();
        addUserTest.clickUserRoleOption(userRole);  
        addUserTest.enterEmployeeName(employeeName);
        addUserTest.clickStatusDropdown();
        addUserTest.clickStatusOption(status);
        addUserTest.enterNewUsername(newUsername);
        addUserTest.enterUserPassword(userPassword);
        addUserTest.enterConfirmPassword(confirmPassword);
        addUserTest.clickSaveButton();
        

    }

}
