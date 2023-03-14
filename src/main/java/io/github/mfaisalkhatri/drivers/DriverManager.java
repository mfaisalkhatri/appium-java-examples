package io.github.mfaisalkhatri.drivers;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.USE_DRIVERS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.USE_PLUGINS;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class DriverManager {

    private static final String APP_PATH = String.valueOf(Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "app", "webdriverio-app.apk"));
    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");
    private static AppiumDriverLocalService service;

    public static void createAndroidDriver() throws MalformedURLException {
        startServer();
        setDriver(new AndroidDriver(service.getUrl(), uiAutomator2Options()));
        //setDriver(new AndroidDriver(new URL("http://localhost:4723/wd/hub"), uiAutomator2Options()));
        setupDriverTimeouts();
    }

    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer ();
        }
    }

    @SuppressWarnings("unchecked")
    public static <D extends AppiumDriver> D getDriver() {
        return (D) DriverManager.DRIVER.get();
    }

    private static <D extends AppiumDriver> void setDriver(final D driver) {
        DriverManager.DRIVER.set(driver);
    }

    private DriverManager() {
    }

    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(30));
    }

    private static DesiredCapabilities setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
        capabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
        capabilities.setCapability("appPackage", "com.wdiodemoapp");
        capabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
        capabilities.setCapability("noReset", false);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        return capabilities;
    }

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

    private static UiAutomator2Options uiAutomator2Options() {

        UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd("Pixel_2_API_33")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Pixel_2_API_33")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(APP_PATH)
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.MainActivity")
                .setNoReset(false);
        return uiAutomator2Options;
    }

    public static void startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1")
                .usingPort(4723)
                //.usingDriverExecutable (new File ("E:\\Program Files\\nodejs\\node.exe"))
                .withArgument(BASEPATH, "/wd/hub")
                .withArgument(SESSION_OVERRIDE)
                .withArgument(LOG_LEVEL, "debug")
                .withArgument(USE_DRIVERS, "uiautomator2")
                .withArgument(USE_PLUGINS, "element-wait");

        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    public static void stopServer() {
        service.stop();
    }

}
