package io.github.mfaisalkhatri.android.pages.proverbial;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class Notifications {


    private WebElement expandNotificationBtn() {
        return getDriver().findElements(AppiumBy.id("android:id/expand_button_touch_container")).get(0);
    }

    private WebElement notificationBtn() {
        return getDriver().findElement(AppiumBy.id("com.lambdatest.proverbial:id/notification"));
    }

    private WebElement notificationBar() {
        return getDriver().findElement(AppiumBy.id("com.lambdatest.proverbial:id/action_bar"));

    }

    public void openNotificationPanel() {
        getDriver().openNotifications();
    }

    public boolean checkNotificationIsDisplayed() {
        notificationBtn().click();
        return notificationBar().isDisplayed();
    }

    public String getAppNotificationTitle() {
        expandNotificationBtn().click();
        return getDriver().findElements(AppiumBy.id("android:id/app_name_text")).get(0).getText();
    }

    public String getNotificationTitle() {
        return getDriver().findElement(AppiumBy.id("android:id/title")).getText();
    }

    public String getNotificationText() {
        return getDriver().findElement(AppiumBy.id("android:id/text")).getText();
    }

}
