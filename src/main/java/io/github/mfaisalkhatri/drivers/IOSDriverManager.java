package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;

import static io.github.mfaisalkhatri.server.AppiumServerManager.stopServer;

public class IOSDriverManager {

    private static final ThreadLocal<IOSDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");

    private static final String APP_PATH = String.valueOf(Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "wdioNativeDemoApp.app"));

    public static IOSDriver getDriver() {
        return IOSDriverManager.DRIVER.get();
    }

    private static void setDriver(final IOSDriver driver) {
        IOSDriverManager.DRIVER.set(driver);
    }


    private static XCUITestOptions xcuiTestOptions() {
        return new XCUITestOptions()
                .setDeviceName("iPhone 15 Pro Max")
                .setAutomationName(AutomationName.IOS_XCUI_TEST)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setPlatformVersion("17.0")
                .setApp(APP_PATH)
                .setNoReset(false);
    }

    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer();
        }
    }

    public static void createIOSDriver() {
        //startServer("ios");
        //setDriver(new IOSDriver(getService().getUrl(), xcuiTestOptions()));
        try {
            setDriver(new IOSDriver(new URL("http://127.0.0.1:4723/"), xcuiTestOptions()));
        } catch (final MalformedURLException e) {
            throw new RuntimeException(e);
        }
        setupDriverTimeouts();
    }

    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }
}
