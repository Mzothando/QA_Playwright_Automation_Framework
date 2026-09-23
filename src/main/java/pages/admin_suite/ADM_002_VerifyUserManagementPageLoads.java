package pages.admin_suite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.LocatorReader;

public class ADM_002_VerifyUserManagementPageLoads {

    private final Page page;

    //=== Constructor ===
    public ADM_002_VerifyUserManagementPageLoads(Page page) {
        this.page = page;
    }

    //Element Locator Methods
    private Locator adminTab() {
        return page.locator(LocatorReader.getLocator("adminPage.adminTab"));
    }
    private Locator userManagementDropdown() {
        return page.locator(LocatorReader.getLocator("adminPage.userManagement.dropdown_xpath"));
    }
    private Locator usersLink() {
        return page.locator(LocatorReader.getLocator("adminPage.users.link_xpath"));
    }
    private Locator usernameField() {
        return page.locator(LocatorReader.getLocator("adminPage.users.usernameInput_xpath"));
    }
    private Locator userRoleField() {
        return page.locator(LocatorReader.getLocator("adminPage.users.userRoleDropdown_xpath"));
    }
    private Locator employeeNameField() {
        return page.locator(LocatorReader.getLocator("adminPage.users.employeeNameInput_xpath"));
    }
    private Locator statusField() {
        return page.locator(LocatorReader.getLocator("adminPage.users.statusDropdown_xpath"));
    }
    private Locator searchButton() {
        return page.locator(LocatorReader.getLocator("adminPage.users.searchButton_xpath"));
    }
    private Locator resetButton() {
        return page.locator(LocatorReader.getLocator("adminPage.users.resetButton_xpath"));
    }
    private Locator addUserButton() {
        return page.locator(LocatorReader.getLocator("adminPage.users.addButton_xpath"));
    }
    private Locator usersRecordsTable() {
        return page.locator(LocatorReader.getLocator("adminPage.users.table_xpath"));
    }


    

   

    // Action Methods
    public void clickAdminTab() {
        adminTab().click();
    }

    public void clickUserManagementDropdown() {
        userManagementDropdown().click();
    }

    public void clickUsersLink() {
        usersLink().click();
    }
    public boolean isUsernameFieldVisible() {
        return usernameField().isVisible();
    }

    public boolean isUserRoleFieldVisible() {
        return userRoleField().isVisible();
    }

    public boolean isEmployeeNameFieldVisible() {
        return employeeNameField().isVisible();
    }

    public boolean isStatusFieldVisible() {
        return statusField().isVisible();
    }

    public boolean isSearchButtonVisible() {
        return searchButton().isVisible();
    }

    public boolean isResetButtonVisible() {
        return resetButton().isVisible();
    }

    public boolean isAddUserButtonVisible() {
        return addUserButton().isVisible();
    }

    public boolean isUsersRecordsTableVisible() {
        return usersRecordsTable().isVisible();
    }
  

}
