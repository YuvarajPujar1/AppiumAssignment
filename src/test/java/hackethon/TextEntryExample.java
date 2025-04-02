package hackethon;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TextEntryExample {
    @Test
    public void textEntryTest() throws MalformedURLException {
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

        // Click on "Text Entry Dialog"
        WebElement textEntryDialog = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/text_entry_button")));
        textEntryDialog.click();

        // Enter Name
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/username_edit")));
        nameField.sendKeys("Yuvaraj_Pujar");

        // Enter Password
        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/password_edit")));
        passwordField.sendKeys("Heal@123");

        System.out.println("Entered Name & Password!");

        // Click OK to save details
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1")));
        okButton.click();

        System.out.println("Details saved successfully!");

        driver.quit();
    }
}
