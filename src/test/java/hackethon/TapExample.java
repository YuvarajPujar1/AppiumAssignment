package hackethon;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class TapExample {
    @Test
    public void tapTest() throws MalformedURLException {
        // Set up Appium options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Initialize driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Animation -> View Flip
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Animation"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("View Flip"))).click();

        // Locate "Flip" button
        WebElement flipButton = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/button")));

        // Perform tap action
        tap(driver, flipButton);

        System.out.println("Flip button tapped successfully!");
        driver.quit();
    }

    public void tap(AndroidDriver driver, WebElement element) {
        // Get element coordinates
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        // Using W3C Actions API for tap
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }
}
