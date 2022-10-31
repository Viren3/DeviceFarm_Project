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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
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
    public static WebDriver webDriver;
    public static AndroidDriver<AndroidElement> mobileDriver;
    public AppiumDriverLocalService appiumService;
    public AppiumServiceBuilder builder;
    @BeforeTest
    public void mobileSetup() throws MalformedURLException {

        File f = new File("Application_build");
        File fileName = new File(f, "yoCricket-v23.09.01.apk");

        try {
            if (appiumService == null) {
                boolean flag = checkIfServerIsRunnning(4723);
                if (!flag) {
                    appiumService = AppiumDriverLocalService.buildDefaultService();
                    appiumService.start();
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        log.info("Mobile service is started successfully.");

        String appiumServiceUrl = appiumService.getUrl().toString();
        System.out.println("Appium Service Address : - " + appiumServiceUrl);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy_Nexus_API");
        cap.setCapability(MobileCapabilityType.APP, fileName.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        cap.setCapability("platformName", "Android");
        cap.setCapability("autoGrantPermissions", true);
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        mobileDriver = new AndroidDriver<AndroidElement>(new URL(appiumServiceUrl), cap);
        mobileDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);

            serverSocket.close();
        } catch (IOException e) {

            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
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
        mobileDriver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void mobileTearDown() {
        if (appiumService != null) {
            appiumService.stop();
        }
    }
}