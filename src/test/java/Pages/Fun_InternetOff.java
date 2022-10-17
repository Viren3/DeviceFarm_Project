package Pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Fun_InternetOff extends BaseClass {
    WebDriver webDriver;

    public static Logger log = LogManager.getLogger(Fun_InternetOff.class.getName());

    public Fun_InternetOff(WebDriver remoteDriver) {
        webDriver = remoteDriver;
    }

//    public void testDemo(){webDriver.get(baseUrlStr);}

    public void countryCodeDropdown() {
        Elements el = new Elements(webDriver);
        el.countryCodeDropdown.click();

    }
}
