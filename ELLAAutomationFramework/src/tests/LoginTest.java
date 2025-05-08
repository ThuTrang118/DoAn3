package tests;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class LoginTest extends BaseTest {
	LoginPage loginPage;

	@BeforeMethod
    public void setUpTest() {
        setup();
        loginPage = new LoginPage(driver);
    }

	@Test(priority = 1)
    public void testValidLogin() {
        loginPage.login("ThuTrang1182004@gmail.com", "abcdef");
        boolean isDashboardDisplayed = driver.findElements(By.xpath("//*[@id=\"thegioidoda-top1\"]/div/div/div/div[1]/div/a[1]/span")).size() > 0;
        Assert.assertTrue(isDashboardDisplayed, "Login thành công");
    }

	@Test(priority = 2)
	public void testLoginWrongPassword() {
	    loginPage.login("ThuTrang1182004@gmail.com", "saimatkhau");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"WGR_html_alert\"]/div")));
	    String error = errorElement.getText();
	    System.out.println("Thông báo lỗi: " + error);
	    Assert.assertTrue(error.contains("Sai mật khẩu") || error.contains("Tài khoản hoặc mật khẩu không chính xác"), "Không đúng thông báo khi mật khẩu sai");
	}

	  
	@Test(priority = 3)
	public void testLoginInvalidEmailFormat() {
	    loginPage.login("email", "abcdef");
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"WGR_html_alert\"]/div")));
	    String error = errorElement.getText();
	    System.out.println("Thông báo lỗi: " + error);
	    Assert.assertTrue(error.contains("Tài khoản hoặc mật khẩu không chính xác"));
	}


    @AfterMethod
    public void tearDownTest() {
        teardown();
    }
}