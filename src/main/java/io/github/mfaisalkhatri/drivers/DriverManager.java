package io.github.mfaisalkhatri.drivers;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;

import java.io.File;
import java.time.Duration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class DriverManager {

    private static final String APP_PATH = System.getProperty ("user.dir") + "\\src\\test\\resources\\app\\webdriverio-app.apk";

    private static final ThreadLocal<AppiumDriver> DRIVER = new ThreadLocal<> ();
    private static final Logger                    LOG    = LogManager.getLogger ("DriverManager.class");
    private static       AppiumDriverLocalService  service;

    public static void createAndroidDriver () {
        startServer ();
        DRIVER.set (new AndroidDriver (service.getUrl (), setCapabilities ()));
        setupDriverTimeouts ();
    }

    public static void quitSession () {
        if (null != DRIVER.get ()) {
            LOG.info ("Closing the driver...");
            getDriver ().quit ();
            DRIVER.remove ();
            stopServer ();
        }
    }

    public static <D extends AppiumDriver> D getDriver () {
        return (D) DriverManager.DRIVER.get ();
    }

    private static <D extends AppiumDriver> void setDriver (final D driver) {
        DriverManager.DRIVER.set (driver);
    }

    private DriverManager () {
    }

    private static void setupDriverTimeouts () {
        getDriver ().manage ()
            .timeouts ()
            .implicitlyWait (Duration.ofSeconds (30));
    }

    private static DesiredCapabilities setCapabilities () {
        DesiredCapabilities capabilities = new DesiredCapabilities ();
        capabilities.setCapability (MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        capabilities.setCapability (MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
        capabilities.setCapability (MobileCapabilityType.APP, APP_PATH);
        capabilities.setCapability ("appPackage", "com.wdiodemoapp");
        capabilities.setCapability ("appActivity", "com.wdiodemoapp.MainActivity");
        capabilities.setCapability ("noReset", false);
        capabilities.setCapability (MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
        return capabilities;
    }

    public static void startServer () {
        AppiumServiceBuilder builder = new AppiumServiceBuilder ();
        builder.withIPAddress ("127.0.0.1")
            .usingPort (4723)
            .withAppiumJS (
                new File ("C:\\Users\\Faisal Khatri\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
            .usingDriverExecutable (new File ("E:\\Program Files\\nodejs\\node.exe"))
            .withArgument (BASEPATH, "/wd/hub")
            .withArgument (GeneralServerFlag.SESSION_OVERRIDE)
            .withArgument (GeneralServerFlag.LOG_LEVEL, "debug");

        service = AppiumDriverLocalService.buildService (builder);
        service.start ();
    }

    public static void stopServer () {
        service.stop ();
    }

}
