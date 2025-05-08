package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
	private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By emailField = By.name("t_email");
    private By passwordField = By.name("t_matkhau");
    private By loginButton = By.xpath("//*[@id='oi_popup_inner']/div/div[2]/form/div[7]/button");
    private By alertMessage = By.xpath("//*[@id='WGR_html_alert']/div");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public void openLoginPopup() {
        By loginIcon = By.xpath("//*[@id=\"thegioidoda-top1\"]/div/div/div/div[1]/div/a[1]/span");
        wait.until(ExpectedConditions.elementToBeClickable(loginIcon)).click();
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        driver.findElement(loginButton).click();
    }

    public boolean isValidationMessageDisplayed() {
        WebElement email = driver.findElement(emailField);
        return !email.getAttribute("value").isEmpty();
    }

    public boolean isLoginErrorDisplayed() {
        return driver.findElements(alertMessage).size() > 0;
    }

    public String getLoginErrorMessage() {
        return driver.findElement(alertMessage).getText();
    }
}