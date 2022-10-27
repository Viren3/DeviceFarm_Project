package Pages;

import Utilities.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidDriver;
import Utilities.ExtendsReportClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;


public class Fun_InternetOff extends BaseClass {
    AndroidDriver mobileDriver;

    public static Logger log = LogManager.getLogger(Fun_InternetOff.class.getName());

    public Fun_InternetOff(AndroidDriver remoteDriver) {
        mobileDriver = remoteDriver;
    }

//    public void testDemo(){webDriver.get(baseUrlStr);}

    public void addNumber() throws InterruptedException {
        Elements el = new Elements(mobileDriver);

        mobileDriver.findElement(el.clickonTextbox).click();
        Thread.sleep(3000);
//        ExtendsReportClass.getTest().log(LogStatus.PASS, "Click on Back icon in Browser ", "User should be able to click on Back icon.");
        mobileDriver.findElement(By.className("android.widget.EditText")).sendKeys("7567543247");
        Thread.sleep(4000);
        log.info("testing 2");
        mobileDriver.hideKeyboard();
//        mobileDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView")).click();
//        Thread.sleep(2000);
//        mobileDriver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup")).click();

    }
}
