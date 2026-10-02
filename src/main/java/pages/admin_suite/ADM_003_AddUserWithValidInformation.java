package pages.admin_suite;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.SelectOption;

import utils.LocatorReader;

public class ADM_003_AddUserWithValidInformation {

    private final Page page;

    //=== Constructor ===
    public ADM_003_AddUserWithValidInformation(Page page) {
        this.page = page;
    }

    // Elements Locator Methods
    public Locator UsernameField() {
        return page.locator(LocatorReader.getLocator("loginPage.username"));
        
    }

    public Locator PasswordField() {
        return page.locator(LocatorReader.getLocator("loginPage.password"));
    }

    public Locator LoginButton() {
        return page.locator(LocatorReader.getLocator("loginPage.loginButton"));
    }
    private Locator adminTab() {
        return page.locator(LocatorReader.getLocator("adminPage.adminTab"));
    }
    private Locator userManagementDropdown() {
        return page.locator(LocatorReader.getLocator("adminPage.userManagement.dropdown_xpath"));
    }
    private Locator usersLink() {
        return page.locator(LocatorReader.getLocator("adminPage.users.link_xpath"));
    }
    private Locator addUserButton() {
        return page.locator(LocatorReader.getLocator("adminPage.users.addButton_xpath"));
    }
    private Locator userRoleDropdown() {
        return page.locator(LocatorReader.getLocator("adminPage.users.userRoleDropdown_xpath"));
    }
    private Locator userRoleOption() {
        return page.locator(LocatorReader.getLocator("adminPage.users.options_xpath"));
    }
    private Locator employeeNameInput() {
        return page.locator(LocatorReader.getLocator("adminPage.users.employeeNameInput_xpath"));
    }
    private Locator statusDropdown() {
        return page.locator(LocatorReader.getLocator("adminPage.users.statusDropdown_xpath"));
    }
    private Locator usernameInput() {
        return page.locator(LocatorReader.getLocator("adminPage.users.usernameInput_xpath"));
    }
    private Locator passwordInput() {
        return page.locator(LocatorReader.getLocator("adminPage.users.passwordInput_xpath"));
    }
    private Locator confirmPasswordInput() {
        return page.locator(LocatorReader.getLocator("adminPage.users.confirmPasswordInput_xpath"));
    }
    private Locator saveButton() {
        return page.locator(LocatorReader.getLocator("adminPage.users.saveButton_xpath"));
    }



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

    public void clickUserManagementDropdown() {
        userManagementDropdown().click();
    }

    public void clickUsersLink() {
        usersLink().click();
    }

    public void clickAddUserButton() {
        addUserButton().click();
    }

    public void clickUserRoleDropdown() {
        userRoleDropdown().click();
    }
    public void clickUserRoleOption(String option) {
        userRoleOption().selectOption(new SelectOption().setLabel(option));
    }
    public void enterEmployeeName(String employeeName) {
        employeeNameInput().fill(employeeName);
    }

    public void clickStatusDropdown() {
        statusDropdown().click();
    }

    public void clickStatusOption(String option) {
        statusDropdown().selectOption(new SelectOption().setLabel(option));
    }

    public void enterNewUsername(String newUsername) {
        usernameInput().fill(newUsername);
    }

    public void enterUserPassword(String userPassword) {
        passwordInput().fill(userPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        confirmPasswordInput().fill(confirmPassword);
    }

    public void clickSaveButton() {
        saveButton().click();
    }

    



}
