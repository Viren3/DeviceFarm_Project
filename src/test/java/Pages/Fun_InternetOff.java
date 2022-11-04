package Pages;
import io.appium.java_client.android.AndroidDriver;
import Utilities.BaseClass;
import Pages.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Fun_InternetOff extends BaseClass {
    AndroidDriver mobileDriver;

    public static Logger log = LogManager.getLogger(Fun_InternetOff.class.getName());

    public Fun_InternetOff(AndroidDriver remoteDriver) {
        mobileDriver = remoteDriver;
    }


    public void addNumber() throws InterruptedException {
        Elements el = new Elements(mobileDriver);

//        mobileDriver.findElement(el.clickonTextbox).click();

        mobileDriver.findElement(By.xpath("//android.widget.EditText[@index='1']")).click();
        Thread.sleep(3000);
        mobileDriver.findElement(By.xpath("//android.widget.EditText[@index='1']")).sendKeys("7567543247");
        Thread.sleep(4000);
        log.info("testing 2");
        mobileDriver.hideKeyboard();
        Thread.sleep(2000);
        mobileDriver.findElement(By.xpath("(//android.widget.TextView[@index='0'])[4]")).click();
        Thread.sleep(2000);
        mobileDriver.findElement(el.btnGetOtp).click();
        Thread.sleep(2000);
    }
}