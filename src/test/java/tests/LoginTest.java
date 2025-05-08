package tests;

import base.BaseTest;
import dataproviders.LoginDataProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

import java.time.Duration;

public class LoginTest extends BaseTest {
	LoginPage loginPage;

    @BeforeMethod
    public void setUpTest() {
        setup();
        loginPage = new LoginPage(driver);
        driver.get("https://ella.vn/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement loginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"thegioidoda-top1\"]/div/div/div/div[1]/div/a[1]/span")));
        loginIcon.click();
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void testLoginDataDriven(String email, String password, String expectedResult) {
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        if (email.isEmpty()) {
            WebElement emailField = driver.findElement(By.name("t_email"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String message = (String) js.executeScript("return arguments[0].validationMessage;", emailField);
            Assert.assertEquals(message.trim(), expectedResult.trim());
        } else if (password.isEmpty()) {
            WebElement passField = driver.findElement(By.name("t_matkhau"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            String message = (String) js.executeScript("return arguments[0].validationMessage;", passField);
            Assert.assertEquals(message.trim(), expectedResult.trim());
        } else {
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"WGR_html_alert\"]/div")));
                String actualError = alert.getText().trim();
                Assert.assertTrue(actualError.contains(expectedResult.trim()));
            } catch (TimeoutException e) {
                Assert.assertTrue(expectedResult.equalsIgnoreCase("Success"), "Không đúng kỳ vọng");
            }
        }
    }

    @AfterMethod
    public void tearDownTest() {
        teardown();
    }
}