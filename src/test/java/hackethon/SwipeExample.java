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

public class SwipeExample {
    @Test
    public void swipeTest() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Views -> Gallery -> Photos
        WebElement views = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Views")));
        views.click();
        WebElement gallery = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Gallery")));
        gallery.click();
        WebElement photos = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("1. Photos")));
        photos.click();

        // Swipe left 4 times to reach the 5th image
        for (int i = 0; i < 4; i++) {
            swipeLeft(driver);
            Thread.sleep(3000);
        }

        System.out.println("5th photo displayed");
        driver.quit();
    }

    public void swipeLeft(AndroidDriver driver) {
        int screenWidth = driver.manage().window().getSize().width;
        int screenHeight = driver.manage().window().getSize().height;
        int startX = (int) (screenWidth * 0.8);
        int endX = (int) (screenWidth * 0.2);
        int startY = screenHeight / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, startY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}
