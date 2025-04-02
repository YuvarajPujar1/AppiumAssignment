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

public class SingleChoiceAlertExample {
    @Test
    public void singleChoiceAlertTest() throws MalformedURLException {
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

        // Click on "Single Choice List"
        WebElement singleChoiceList = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/radio_button")));
        singleChoiceList.click();

        // Select "Street View" radio button using your XPath
        WebElement streetViewOption = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.CheckedTextView[@resource-id='android:id/text1' and @text='Street view']")));
        streetViewOption.click();
        System.out.println("Street View' option selected!");

        // Confirm selection by clicking OK
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1")));
        okButton.click();
        System.out.println("Selection confirmed and saved!");

        driver.quit();
    }
}
