package io.github.mfaisalkhatri.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class ExtentReportListener implements ITestListener {
    ExtentReports extentReports = ExtentManager.getInstance();
    ExtentTest extentTest;

    public void onTestSuccess(ITestResult result) {
        extentTest.pass("Test Passed", MediaEntityBuilder
                .createScreenCaptureFromBase64String(captureScreenshot())
                .build());
    }

    public void onTestFailure(ITestResult result) {
        extentTest.fail("Test Failed " + result.getThrowable().getMessage(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(captureScreenshot())
                .build());
    }

    public void onTestSkipped(ITestResult result) {
        extentTest.skip("Test Skipped " + result.getThrowable().getMessage(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(captureScreenshot())
                .build());
    }

    public void onTestStart(ITestResult result) {
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        extentReports.flush();
    }

    public String captureScreenshot() {
        String userDirectoryPath = System.getProperty("user.dir");
        String screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
        return screenshot;
    }
}
