package io.github.mfaisalkhatri.pages.wdio;

import io.appium.java_client.AppiumBy;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

/**
 * @author Faisal Khatri
 * @since 2/23/2023
 **/
public class HomePage {

    public String getTitle() {
        return getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"WEBDRIVER\")"))
                .getText();
    }

    public void openMenu(String menuName) {
        getDriver().findElement(AppiumBy.accessibilityId(menuName)).click();

    }

    public String tagLine() {
        return getDriver().findElement(
                        AppiumBy.androidUIAutomator("new UiSelector().text(\"Demo app for the appium-boilerplate\")"))
                .getText();
    }

}
