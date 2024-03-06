package io.github.mfaisalkhatri.ios.paralleltests.tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

public class IOSTestOnIPhoneFifteenPro extends BaseTest {

    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "wdioNativeDemoApp.app"));
    private static final ThreadLocal<IOSDriver> IOSDRIVER = new ThreadLocal<>();

    @Test
    public void testFormOnIPhone() {
        final XCUITestOptions xcuiTestOptions = new XCUITestOptions().setPlatformName("iOS")
                .setDeviceName("iPhone 15 Pro")
                .setPlatformVersion("17.2")
                .setUdid("E16563D0-CD8D-473E-BB3F-82A27BC4AEC7")
                .setWdaLocalPort(8101)
                .setApp(APP_PATH)
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setNoReset(false);

        try {
            IOSDRIVER.set(new IOSDriver(new URL("http://localhost:4723"), xcuiTestOptions));
        } catch (final MalformedURLException e) {
            throw new Error("Remote URL is not set correctly!");
        }

        IOSDRIVER.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        testForm(IOSDRIVER.get());
    }

}
