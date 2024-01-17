package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class CheckoutCompletePage {
    private WebElement thankYouText() {
        return getDriver().findElement(AppiumBy.xpath("\t\n" +
                "//android.widget.TextView[@text=\"THANK YOU FOR YOU ORDER\"]\n"));
    }

    private WebElement backHomeButton() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"BACK HOME\"]"));
    }

    private WebElement checkoutCompleteHeader() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"CHECKOUT: COMPLETE!\"]"));
    }

    public boolean isThankYouTextDisplayed() {
        return thankYouText().isDisplayed();
    }

    public String getThankYouTextDisplayed() {
        return thankYouText().getText();
    }

    public void clickBackHomeButton() {
        backHomeButton().click();
    }

    public String getCheckoutCompleteHeaderText() {
        return checkoutCompleteHeader().getText();
    }
}
