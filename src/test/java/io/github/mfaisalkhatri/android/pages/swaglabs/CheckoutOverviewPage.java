package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.utils.MobileTestUtils;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class CheckoutOverviewPage extends MobileTestUtils {
    private WebElement cancelButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-CANCEL"));
    }

    private WebElement finishButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-FINISH"));
    }

    private WebElement panel() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Item"));
    }

    public void clickCancelButton() {
        cancelButton().click();
    }

    public void clickFinishButton() {
        scroll(panel(), ScrollDirection.DOWN, 0.9);
        scroll(panel(), ScrollDirection.DOWN, 0.9);
        finishButton().click();
    }
}
