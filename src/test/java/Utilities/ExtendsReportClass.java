package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ExtendsReportClass extends BaseClass {

    static ExtentReports exReports;
    static Map extentTestMap = new HashMap();;


    public static ExtentReports extendReportGenerator() {

        DateFormat df = new SimpleDateFormat("dd_MMM_yyyy-HH_mm_ss");
        String reportPath = System.getProperty("user.dir") + "/Reports/TestReport"+ df.format(System.currentTimeMillis()) + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Environment : QA");
        reporter.config().setDocumentTitle("Yo Cricket Results");
        reporter.config().setTheme(Theme.STANDARD);
        exReports = new ExtentReports();
        exReports.attachReporter(reporter);
        exReports.setSystemInfo("Environment", "QA Server");
        exReports.setSystemInfo("Test By", "Automation Team");
        return exReports;
    }
    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) Thread.currentThread().getId());
    }

}
