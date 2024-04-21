package io.github.mfaisalkhatri.ios.paralleltests.demo;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<IOSDriver> IOSDRIVER = new ThreadLocal<>();
    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "wdioNativeDemoApp.app"));

    @BeforeClass(alwaysRun = true)
    @Parameters({"devicename", "udid", "platformversion", "wdaLocalPort"})
    public void testFormOnIPhone(final String deviceName, final String udid, final String platformVersion, final int wdaLocalPort) {
        final XCUITestOptions xcuiTestOptions = new XCUITestOptions().setPlatformName("iOS")
                .setDeviceName(deviceName)
                .setPlatformVersion(platformVersion)
                .setUdid(udid)
                .setWdaLocalPort(wdaLocalPort)
                .setApp(APP_PATH)
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setNoReset(false);

        try {
            IOSDRIVER.set(new IOSDriver(new URL("http://localhost:4723"), xcuiTestOptions));
        } catch (final MalformedURLException e) {
            throw new Error("Remote URL is not set correctly!");
        }
    }

    public IOSDriver getIOSDriver() {
        return IOSDRIVER.get();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        getIOSDriver().quit();
    }
}