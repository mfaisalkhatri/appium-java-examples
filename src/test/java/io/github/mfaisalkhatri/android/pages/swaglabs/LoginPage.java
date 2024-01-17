package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class LoginPage {

    private WebElement userNameField() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Username"));
    }

    private WebElement passwordField() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Password"));
    }

    private WebElement loginBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-LOGIN"));
    }

    public String getProductPageTitle() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"PRODUCTS\"]")).getText();
    }

    public void login(String username, String password) {
        userNameField().sendKeys(username);
        passwordField().sendKeys(password);
        loginBtn().click();
    }

    public String getTextAfterLogin() {
        return getProductPageTitle();
    }
}
