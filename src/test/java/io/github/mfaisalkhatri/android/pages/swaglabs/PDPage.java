package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class PDPage {
    public String getProductTitleonPDP() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Sauce Labs Onesie\"]")).getText();
    }

    private WebElement backToProductsButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-BACK TO PRODUCTS"));
    }

    public boolean isBackToProductsButtonDisplayed() {
        return backToProductsButton().isDisplayed();
    }

    public void clickBackToProductsButton() {
        backToProductsButton().click();
    }


}
