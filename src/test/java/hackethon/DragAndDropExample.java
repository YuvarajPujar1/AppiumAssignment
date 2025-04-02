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

public class DragAndDropExample {
    @Test
    public void dragAndDropTest() throws MalformedURLException {
        // Set up Appium options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        // Initialize driver
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Views -> Drag and Drop
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Views"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Drag and Drop"))).click();

        // Locate the source (dot) and target (destination)
        WebElement source = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/drag_dot_1")));
        WebElement target = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.id("io.appium.android.apis:id/drag_dot_2")));

        // Perform drag and drop action
        dragAndDrop(driver, source, target);

        System.out.println("Drag and Drop completed!");
        driver.quit();
    }

    public void dragAndDrop(AndroidDriver driver, WebElement source, WebElement target) {
        // Get coordinates for drag and drop
        int startX = source.getLocation().getX() + (source.getSize().getWidth() / 2);
        int startY = source.getLocation().getY() + (source.getSize().getHeight() / 2);
        int endX = target.getLocation().getX() + (target.getSize().getWidth() / 2);
        int endY = target.getLocation().getY() + (target.getSize().getHeight() / 2);

        // Using W3C Actions API for drag and drop
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragDrop = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), endX, endY))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(dragDrop));
    }
}
