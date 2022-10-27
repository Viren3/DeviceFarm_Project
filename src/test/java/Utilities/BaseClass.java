package Utilities;

import Pages.Fun_InternetOff;
import com.aventstack.extentreports.ExtentReports;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
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


public class BaseClass extends ExtentReports {

    public static Logger log = LogManager.getLogger(Fun_InternetOff.class.getName());
    public Properties properties;
    public static AndroidDriver<AndroidElement> mobileDriver;
    /*Appium Start manually*/
//    AppiumDriver<MobileElement> mobileDriver;
    public AppiumDriverLocalService appiumService;
    public AppiumServiceBuilder builder;
    /* Remove comment while use appium start manually */
//    public static String baseUrlStr = "http://127.0.0.1:4723/wd/hub";


    @BeforeTest
    public void mobileSetup() throws MalformedURLException {
        /*For AWS Devices*/
//        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
//        URL url = new URL(URL_STRING);
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
        /*For AWS Devices*/
//        cap.setCapability("deviceName", "Android Emulator");
//        cap.setCapability("appPackage","com.amazonaws.devicefarm.android.referenceapp");
//        cap.setCapability("appActivity","com.amazonaws.devicefarm.android.referenceapp.Activities.MainActivity");
        cap.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        /*For AWS Devices*/
//        mobileDriver = new AndroidDriver<AndroidElement>(url, cap);
        mobileDriver = new AndroidDriver<AndroidElement>(new URL(appiumServiceUrl), cap);
        mobileDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        /* Remove comment while use appium start manually */
//        URL url = new	  URL("http://127.0.0.1:4723/wd/hub");
//        mobileDriver = new AppiumDriver<MobileElement>(url,cap);
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
