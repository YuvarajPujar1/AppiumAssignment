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
import java.util.List;

public class RepeatAlarmExample {
    @Test
    public void repeatAlarmTest() throws MalformedURLException {
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

        // Click on "Repeat Alarm"
        WebElement repeatAlarm = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("io.appium.android.apis:id/checkbox_button")));
        repeatAlarm.click();

        // List of all days checkboxes
//        List<WebElement> days = driver.findElements(AppiumBy.id("android:id/select_dialog_listview"));
//        List<WebElement> days = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                AppiumBy.id("android:id/select_dialog_listview")));


        List<WebElement> days = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                AppiumBy.xpath("//android.widget.ListView/android.widget.CheckedTextView")));

        // Debug: Print number of checkboxes found
        System.out.println("Total days found: " + days.size());

        if (days.size() >= 7) {
            // Uncheck Saturday (index 5) and Sunday (index 6) if they are checked
            if (days.get(5).getAttribute("checked").equals("true")) {
                days.get(5).click();
                System.out.println("Unchecked Saturday");
            }

            if (days.get(6).getAttribute("checked").equals("true")) {
                days.get(6).click();
                System.out.println("Unchecked Sunday");
            }

            // Check Monday to Friday if they are not already checked
            for (int i = 0; i < 5; i++) {
                if (days.get(i).getAttribute("checked").equals("false")) {
                    days.get(i).click();
                    System.out.println("Checked: " + days.get(i).getText());
                }
            }
            System.out.println("Updated repeat alarm settings!");

            // Click OK to save changes
            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1")));
            okButton.click();

            System.out.println("Repeat alarm updated successfully!");

            driver.quit();
        }
    }
}
