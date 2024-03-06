package io.github.mfaisalkhatri.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.gecko.options.GeckoOptions;
import io.appium.java_client.remote.AutomationName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AndroidDriverManager {

    private static final String APP_PATH = String.valueOf(
            Path.of(System.getProperty("user.dir"), "/src/test/resources/app", "webdriverio-app.apk"));
    private static final ThreadLocal<AndroidDriver> DRIVER = new ThreadLocal<>();
    private static final Logger LOG = LogManager.getLogger("DriverManager.class");
    private static final ThreadLocal<AppiumDriver> DRIVER1 = new ThreadLocal<>();

    public static void createAndroidDriver() {
        //startServer("android");
        //  setDriver(new AndroidDriver(getService().getUrl(), uiAutomator2OptionsWdio()));
        try {
            //setDriver (new AndroidDriver (new URL ("http://127.0.0.1:4723/wd/hub"), geckoOptionsFirefox ()));
            setDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/"), uiAutomator2OptionsWdio()));
        } catch (final MalformedURLException e) {
            throw new Error("Error while setting up Android Driver", e);
        }
        setupDriverTimeouts();
    }

    public static AppiumDriver getAppiumDriver() {
        return AndroidDriverManager.DRIVER1.get();
    }

    public static AndroidDriver getDriver() {
        return AndroidDriverManager.DRIVER.get();
    }

    private static void setDriver(final AndroidDriver driver) {
        AndroidDriverManager.DRIVER.set(driver);
    }

    public static void quitSession() {
        if (null != DRIVER.get()) {
            LOG.info("Closing the driver...");
            getDriver().quit();
            DRIVER.remove();
            //   stopServer();
        }
    }

    private static GeckoOptions geckoOptionsFirefox() {
        final HashMap<String, Object> firefoxOptions = new HashMap<>();
        firefoxOptions.put("androidPackage", "org.mozilla.firefox");
        //firefoxOptions.put ("androidDeviceSerial", "EMULATOR32X1X12X0");
        final GeckoOptions geckoOptions;
        geckoOptions = new GeckoOptions().setPlatformName("mac")
                .setAutomationName(AutomationName.GECKO)
                .setMozFirefoxOptions(firefoxOptions)
                .setAndroidStorage("app")
                .setAcceptInsecureCerts(true)
                .setNoReset(false);
        return geckoOptions;
    }

    private static void setupDriverTimeouts() {
        getDriver().manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(5));
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

    private static UiAutomator2Options uiAutomator2OptionsChrome() {

        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_XL_API_33")
                .setAvdLaunchTimeout(Duration.ofSeconds(300))
                .setAvdReadyTimeout(Duration.ofSeconds(100))
                .setDeviceName("Pixel_XL_API_33")
                .setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2)
                .withBrowserName("chrome")
                .setAutoGrantPermissions(true)
                .setNoReset(false);

        return uiAutomator2Options;
    }

    private static UiAutomator2Options uiAutomator2OptionsProverbial() {

        final UiAutomator2Options uiAutomator2Options;
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

    private static UiAutomator2Options uiAutomator2OptionsWdio() {
        final UiAutomator2Options uiAutomator2Options;
        uiAutomator2Options = new UiAutomator2Options().setAvd("Pixel_6_API_34")
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

}
