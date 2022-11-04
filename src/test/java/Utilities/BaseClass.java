package Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import Pages.Fun_InternetOff;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseClass {

    public static Logger log = LogManager.getLogger(Fun_InternetOff.class.getName());
    public Properties properties;
    public static AndroidDriver<AndroidElement> mobileDriver;
    public AppiumDriverLocalService appiumService;
    public AppiumServiceBuilder builder;
    @BeforeTest
    public void mobileSetup() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();

        // Set your access credentials
        caps.setCapability("browserstack.user", "virenchauhan_nxBqK3");
        caps.setCapability("browserstack.key", "TD2Lrv8zpXUhMtHgHo5n");

        // Set URL of the application under test
        caps.setCapability("app", "bs://ce599abb84f497d5283f02a5fe99cf848cfe9ede");

        // Specify device and os_version for testing
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");

        caps.setCapability("autoGrantPermissions", "true");
        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        mobileDriver = new AndroidDriver<AndroidElement>(
                new URL("http://hub.browserstack.com/wd/hub"), caps);
        mobileDriver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
        // Write your test case statements here

    }

    public String getScreenshotPath(String TestCaseName) throws IOException, InterruptedException {
        String dateName = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
        File source = mobileDriver.getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir")+"/Reports/Screenshots/"+TestCaseName+dateName+".png";
        FileUtils.copyFile(source,new File(destFile));
        return destFile;
    }
    @AfterClass
    public void teradown(){
//        JavascriptExecutor jse = (JavascriptExecutor) mobileDriver;
//        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"<passed/failed>\"}}");
        mobileDriver.quit();
    }

}