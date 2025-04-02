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

public class ScrollExample {
    @Test
    public void scrollTest() throws MalformedURLException, InterruptedException {
        // Set up Appium options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Start Android Driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");

        // Wait for elements to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Open "Views"
        WebElement views = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Views")));
        views.click();
        System.out.println("Clicked on 'Views'");

        // Step 2: Scroll Down until "WebView3" is visible
        scrollToElement(driver, "WebView3");
        System.out.println("Scrolled down to 'WebView3'");

        // Step 3: Scroll Up until "Animation" is visible
        scrollToElement(driver, "Animation");
        System.out.println("Scrolled up to 'Animation'");

        // Step 4: Tap on "Animation"
        WebElement animation = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Animation")));
        animation.click();
        System.out.println("Clicked on 'Animation'");

        // Close the app after execution
        driver.quit();
        System.out.println("Test execution completed!");
    }

    // Method to scroll to a specific element using UiScrollable
    public void scrollToElement(AndroidDriver driver, String text) {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
    }
}
