package io.github.mfaisalkhatri.ios.paralleltests.tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class IOSTestOnIPhoneFifteen extends BaseTest {

    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "wdioNativeDemoApp.app"));
    private static final ThreadLocal<IOSDriver> IOSDRIVER = new ThreadLocal<>();

    @Test
    public void testFormOnIPhone() {
        final XCUITestOptions xcuiTestOptions = new XCUITestOptions().setPlatformName("iOS")
                .setDeviceName("iPhone 15")
                .setPlatformVersion("17.2")
                .setUdid("BB7125C2-174B-43D7-9B88-A982C40C006A")
                .setWdaLocalPort(8100)
                .setApp(APP_PATH)
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setPlatformVersion("16.2")
                .setNoReset(false);

        try {
            IOSDRIVER.set(new IOSDriver(new URL("http://localhost:4723"), xcuiTestOptions));
        } catch (final MalformedURLException e) {
            throw new Error("Remote URL is not set correctly!");
        }

        testForm(IOSDRIVER.get());
    }
}
