package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    By emailInput = By.name("t_email");
    By passwordInput = By.name("t_matkhau");
    By loginButton = By.xpath("//*[@id=\"oi_popup_inner\"]/div/div[2]/form/div[7]/button");
    By loginLink = By.xpath("//*[@id=\"thegioidoda-top1\"]/div/div/div/div[1]/div/a[1]/span");  // Liên kết đăng nhập

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void enterEmail(String email) {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        link.click();
    }
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    public void clickLoginButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        button.click();
    }

    public void login(String email, String password) {
        clickLoginLink();  // Bước 1: Click vào liên kết đăng nhập
        enterEmail(email);  // Bước 2: Nhập email
        enterPassword(password);  // Bước 3: Nhập mật khẩu
        clickLoginButton();  // Bước 4: Click vào nút đăng nhập
    }
}