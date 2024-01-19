package io.github.mfaisalkhatri.listeners;

import io.github.mfaisalkhatri.logging.Log;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class Retry implements IRetryAnalyzer {
    private int count = 0;
    private static int maxTry = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (count < maxTry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                return true;
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        Log.info("The value of 'count' was " + count);
        return false;
    }
}
