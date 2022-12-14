package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


import java.io.IOException;

public class Listeners extends BaseClass implements ITestListener {

    ExtentReports extentReport = ExtendsReportClass.extendReportGenerator();
    ExtentTest test;
//    AppiumDriver driver;

    public void onTestStart(ITestResult result) {

        test = extentReport.createTest(result.getMethod().getMethodName());

    }

    public void onTestSuccess(ITestResult result) {

        test.log(Status.PASS,"Successful!");
    }

    public void onTestFailure(ITestResult result) {

        test.fail(result.getThrowable());
//        try {
//            driver = (AppiumDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }

        try {
            try {
                test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName()),result.getMethod().getMethodName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {

        System.out.println("Skip of test cases and its details are : "+result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

        System.out.println("Failure of test cases and its details are : "+result.getName());
    }

    public void onStart(ITestContext context) {

    }

    public void onFinish(ITestContext context) {

        extentReport.flush();

    }
}