package hackethon;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class HandleAlertExample {
    @Test
    public void handleSimpleAlertTest() throws MalformedURLException {
        // Set up Appium options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Initialize driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to App -> Alert Dialogs
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("App"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Alert Dialogs"))).click();

        // Click on the first alert option
        WebElement firstAlertButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/two_buttons")));
        firstAlertButton.click();

        // Wait for the alert to be visible
        wait.until(ExpectedConditions.alertIsPresent());

        // Capture and extract the alert text
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("Alert Text: " + alertText);

        // Accept (OK) the alert
        alert.accept();
        System.out.println("Alert accepted successfully!");

        driver.quit();
    }
}
