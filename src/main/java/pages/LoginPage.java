package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import utils.LocatorReader;

public class LoginPage {

    private final Page page;

    // Constructor
    public LoginPage(Page page) {
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
    
    // Action Methods
    public void enterUsername() {
        UsernameField().fill("Admin");
    }

    public void enterPassword() {
        PasswordField().fill("admin123");
    }

    public void clickLoginButton() {
        LoginButton().click();
    }

}
