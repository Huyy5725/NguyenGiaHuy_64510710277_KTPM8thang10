package vn.utc.automation.pages;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import vn.utc.automation.base.BasePage;

public class LoginPage extends BasePage {
    private static final String URL = "https://vanphongdientu.utc.edu.vn/Login";
    private static final By USERNAME_INPUT = By.name("username");
    private static final By PASSWORD_INPUT = By.name("userpwd");
    private static final By SUBMIT_BUTTON = By.cssSelector("input.submit_login");
    private static final By PAGE_BODY = By.tagName("body");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(URL);
        until(ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        return this;
    }

    public void submitCredentials(String username, String password) {
        WebElement usernameInput = until(
                ExpectedConditions.visibilityOfElementLocated(USERNAME_INPUT));
        WebElement passwordInput = until(
                ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));

        usernameInput.clear();
        usernameInput.sendKeys(username);
        passwordInput.clear();
        passwordInput.sendKeys(password);
        until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON)).click();
    }

    public boolean waitForAuthenticatedPage() {
        return until(webDriver -> {
            String path = URI.create(webDriver.getCurrentUrl()).getPath();
            return path != null && !path.replaceAll("/+$", "").equalsIgnoreCase("/Login");
        });
    }

}
