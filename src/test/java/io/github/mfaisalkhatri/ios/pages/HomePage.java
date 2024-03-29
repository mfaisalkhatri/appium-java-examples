package io.github.mfaisalkhatri.ios.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static io.github.mfaisalkhatri.drivers.IOSDriverManager.getDriver;

public class HomePage {

    private final WebDriverWait wait;

    public HomePage() {
        this.wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    public String getTitle() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"WEBDRIVER\"`]"))).getText();
    }

    public void openMenu(final String menuName) {
        getDriver().findElement(AppiumBy.accessibilityId(menuName)).click();

    }

    public String tagLine() {
        return this.wait.until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.accessibilityId("Demo app for the appium-boilerplate"))).getText();
    }

}
