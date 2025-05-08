package utils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
public class WaitHelper {
	 WebDriver driver;

	    public WaitHelper(WebDriver driver) {
	        this.driver = driver;
	    }

	    public void waitForElementVisible(WebElement element, int seconds) {
	        new WebDriverWait(driver, Duration.ofSeconds(seconds))
	            .until(ExpectedConditions.visibilityOf(element));
	    }
	}