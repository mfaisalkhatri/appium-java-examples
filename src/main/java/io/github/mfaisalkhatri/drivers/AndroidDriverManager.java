package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.time.Duration;

import static io.github.mfaisalkhatri.server.AppiumServerManager.*;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AndroidDriverManager {
    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "webdriverio-app.apk"));
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");

    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            stopServer();
        }
    }

    public static AndroidDriver getDriver() {
        return AndroidDriverManager.DRIVER.get();
    }

    private static void setDriver(AndroidDriver driver) {
        AndroidDriverManager.DRIVER.set(driver);
    }

    private static UiAutomator2Options uiAutomator2OptionsWdio() {
        UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options()
                .setAvd("Pixel_XL_API_33")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Pixel_XL_API_33")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(APP_PATH)
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.MainActivity")
                .setNoReset(false);
        return uiAutomator2Options;
    }

    private static UiAutomator2Options uiAutomator2OptionsProverbial() {

        UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_XL_API_33")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Pixel_XL_API_33")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .setApp(APP_PATH)
                .setAutoGrantPermissions(true)
                .setAppPackage("com.lambdatest.proverbial")
                .setAppActivity("com.lambdatest.proverbial.MainActivity")
                .setNoReset(false);
        return uiAutomator2Options;
    }

    private static UiAutomator2Options uiAutomator2OptionsChrome() {

        UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_6_API_31")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Pixel_6_API_31")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .withBrowserName("chrome")
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return uiAutomator2Options;
    }


//    private static DesiredCapabilities setCapabilities() {
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
//        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_5_API_30");
//        capabilities.setCapability(MobileCapabilityType.APP, APP_PATH);
//        capabilities.setCapability("appPackage", "com.wdiodemoapp");
//        capabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");
//        capabilities.setCapability("noReset", false);
//        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
//        return capabilities;
//    }


    public static void createAndroidDriver() {
        startServer();
        setDriver(new AndroidDriver(getService().getUrl(), uiAutomator2OptionsWdio()));
//        try {
//            setDriver(new AndroidDriver(new URL("http://localhost:4723/wd/hub"), uiAutomator2OptionsWdio()));
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
        setupDriverTimeouts();
    }

    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
    }


}
