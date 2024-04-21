package io.github.mfaisalkhatri.ios.paralleltests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriverWait wait;
    private final IOSDriver iosDriver;

    public HomePage(final IOSDriver iosDriver) {
        this.iosDriver = iosDriver;
        this.wait = new WebDriverWait(this.iosDriver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"WEBDRIVER\"`]"))).getText();
    }

    public void openMenu(final String menuName) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.accessibilityId(menuName))).click();

    }
}
