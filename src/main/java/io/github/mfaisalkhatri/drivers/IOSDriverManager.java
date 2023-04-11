package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;

import java.nio.file.Path;
import java.time.Duration;

public class IOSDriverManager {

    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "wdioNativeDemoApp.app"));

    private static XCUITestOptions xcuiTestOptions() {
        XCUITestOptions xcuiTestOptions = new XCUITestOptions()
                .setDeviceName("iPhone 13")
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setPlatformVersion("15")
                .setApp(APP_PATH)
                .setNoReset(false);
        return xcuiTestOptions;
    }


}
