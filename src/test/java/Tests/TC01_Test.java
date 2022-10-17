package Tests;

import org.testng.annotations.Test;
import Pages.BaseClass;
import Pages.Fun_InternetOff;


public class TC01_Test extends BaseClass {
    @Test
    public void internetOffTest() throws InterruptedException {
        Fun_InternetOff app = new Fun_InternetOff(webDriver);
        app.countryCodeDropdown();


    }
}
