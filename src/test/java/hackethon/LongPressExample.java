package hackethon;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;

public class LongPressExample {
    @Test
    public void longPressTest() throws MalformedURLException {
        // Set up Appium options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Initialize Android Driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver Initialized!");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Navigate to Views
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Views"))).click();

            // Navigate to Expandable Lists
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Expandable Lists"))).click();

            // Navigate to Custom Adapter
            wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("1. Custom Adapter"))).click();

            // Locate "Cat Names"
            WebElement catNames = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@text='Cat Names']")));

            // Perform Long Press using W3C Actions API
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence longPress = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), catNames.getLocation().getX(), catNames.getLocation().getY()))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofSeconds(2)))  // Fix: Using Pause instead of createPause()
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Arrays.asList(longPress));


            System.out.println("Long press performed on 'Cat Names'");

            // Click on "Sample Action"
            WebElement sampleAction = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.TextView[@text='Sample action']")));
            sampleAction.click();

            System.out.println("Clicked on 'Sample Action'");

        } finally {
            // Close Appium session
            driver.quit();
            System.out.println("Test Execution Completed!");
        }
    }
}
