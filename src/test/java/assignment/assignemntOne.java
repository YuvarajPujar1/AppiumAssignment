package assignment;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;


public class assignemntOne {
    @Test
    public void LaunchApk() throws MalformedURLException, URISyntaxException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Medium Phone");
        options.setPlatformName("Android");
        options.setApp(System.getProperty("user.dir")+"/apps/QATestApp-1.1.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        System.out.println("Appium Driver is Successfully initialized");
        driver.findElement(By.id("com.qatestapp:id/emailTextField1")).sendKeys("helloworld@gmail.com");
        driver.findElement(AppiumBy.id("com.qatestapp:id/passwordTextField")).sendKeys("testing1234");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.qatestapp:id/LoginButton\"]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.qatestapp:id/nameTextField")));
        // Now interact with the element
        driver.findElement(By.id("com.qatestapp:id/nameTextField")).sendKeys("Yuvaraj");
        driver.findElement(By.id("com.qatestapp:id/emailTextField")).sendKeys("yuvaraj.pujar@healthgraph.in");
        driver.findElement(By.id("com.qatestapp:id/phoneNumberTextField")).sendKeys("7019544525");
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.qatestapp:id/button2\"]\n")).click();

    }
}
