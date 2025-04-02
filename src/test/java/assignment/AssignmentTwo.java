package assignment;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class AssignmentTwo {
    @Test
    public void AccessibilityApp() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");


        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Access'ibility\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Provider\"]\n")));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Provider\"]\n")).click();
        Thread.sleep(3000);
        driver.findElement(AppiumBy.xpath("//android.widget.LinearLayout/android.view.View")).click();
        System.out.println("after clicking on colour nothing is showing");
        driver.navigate().back();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Accessibility Node Querying\"]\n")).click();
        System.out.println("clicked on accessibility node querying");
        // Wait until the element is visible
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("//android.widget.ListView")));
        // Fetch all the options in the list
        List<WebElement> optionsList = driver.findElements(AppiumBy.className("android.widget.TextView"));

        // Print all the option texts
        for (WebElement option : optionsList) {
            System.out.println("Option: " + option.getText());
            // Fetch all checkboxes in the list
            List<WebElement> checkboxes = driver.findElements(AppiumBy.xpath("//android.widget.CheckBox[@resource-id='io.appium.android.apis:id/tasklist_finished']"));
            // Wait until elements are visible
            WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
            List<WebElement> selectedLabels = wait2.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    AppiumBy.xpath("//android.widget.CheckBox[@resource-id='io.appium.android.apis:id/tasklist_finished' and @checked='true']/preceding-sibling::android.widget.TextView")
            ));
            StringBuilder selectedOptions = new StringBuilder("Selected Options: ");
            int count = selectedLabels.size();
            for (WebElement label : selectedLabels) {
                selectedOptions.append(label.getText()).append(", ");
            }
            // Remove trailing comma
            if (selectedOptions.toString().endsWith(", ")) {
                selectedOptions.setLength(selectedOptions.length() - 2);
            }
            // Print the selected options
            System.out.println(selectedOptions.toString());
            System.out.println("Total Selected Checkboxes before checking and unchecking(by default): " + count);
            // Loop through each checkbox and toggle its state
            for (WebElement checkbox : checkboxes) {
                if (checkbox.isSelected()) {
                    checkbox.click(); // Uncheck if selected
                } else {
                    checkbox.click(); // Check if unselected
                }
            }
            driver.navigate().back();
            driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Custom View\"]")).click();
            System.out.println("clicked on custom view");
            WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait3.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath("(//android.view.View[@text=\"Off\"])[1]")));
            List<WebElement> checkboxes1 = driver.findElements(AppiumBy.xpath("//android.view.View[@text='Off']"));

            // Check if checkboxes exist
            if (checkboxes.isEmpty()) {
                System.out.println("No checkboxes found!");
            } else {
                for (WebElement checkbox : checkboxes1) {
                    checkbox.click(); // Try clicking
                    Thread.sleep(500); // Give time to update UI

                    // Optionally navigate back
                    // Check if state changed
                    String newState = checkbox.getText(); // Get updated text
                    if (newState.equals("Off")) {
                        System.out.println("Checkbox was not there!");
                    } else {
                        System.out.println("checkbox is not working properly");
                    }
                }
            }
            driver.navigate().back();
        }
    }
}
