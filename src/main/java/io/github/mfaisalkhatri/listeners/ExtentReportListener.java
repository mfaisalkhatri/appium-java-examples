package io.github.mfaisalkhatri.listeners;

import com.aventstack.extentreports.MediaEntityBuilder;
import io.github.mfaisalkhatri.logging.Log;
import io.github.mfaisalkhatri.utils.MobileTestUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportListener implements ITestListener {
    ExtentReports extentReports = ExtentManager.getInstance();
    ExtentTest extentTest;

    public void onTestSuccess(ITestResult result) {
        Log.info(result.getMethod().getMethodName() + " Test Passed");
        extentTest.pass(result.getMethod() + "Test Passed", MediaEntityBuilder
                .createScreenCaptureFromBase64String(MobileTestUtils.captureScreenshot())
                .build());
    }

    public void onTestFailure(ITestResult result) {
        Log.error(result.getMethod().getMethodName() + " Test Failed");
        extentTest.fail(result.getMethod().getMethodName() + "Test Failed " + result.getThrowable().getMessage(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(MobileTestUtils.captureScreenshot())
                .build());
    }

    public void onTestSkipped(ITestResult result) {
        Log.info(result.getMethod().getMethodName() + " Test Skipped");
        extentTest.skip(result.getMethod().getMethodName() + "Test Skipped " + result.getThrowable().getMessage(), MediaEntityBuilder
                .createScreenCaptureFromBase64String(MobileTestUtils.captureScreenshot())
                .build());
    }

    public void onTestStart(ITestResult result) {
        Log.info("Creating the test report");
        extentTest = extentReports.createTest(result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext context) {
        Log.info("Flushing the test report data for current session");
        extentReports.flush();
    }
}
