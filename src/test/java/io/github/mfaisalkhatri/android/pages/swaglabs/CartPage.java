package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.utils.MobileTestUtils;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class CartPage extends MobileTestUtils {
    private WebElement yourCartText() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"YOUR CART\"]"));
    }

    private WebElement itemInCart() {
        return getDriver().findElement(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]"));
    }

    private WebElement removeItem() {
        return getDriver().findElement(AppiumBy.xpath("(//android.widget.TextView[@text=\"REMOVE\"])[2]"));
    }

    private WebElement checkoutButton() {
        return getDriver().findElement(AppiumBy.xpath("//android.view.ViewGroup[@content-desc=\"test-CHECKOUT\"]"));
    }

    private WebElement continueShoppingButton() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"CONTINUE SHOPPING\"]"));
    }

    public boolean isYourCartTextDisplayed() {
        return yourCartText().isDisplayed();
    }

    public boolean isItemInCartDisplayed() {
        return itemInCart().isDisplayed();
    }

    public void clickRemoveItem() {
        removeItem().click();
    }

    private WebElement panel() {
        return getDriver().findElement(AppiumBy.xpath(
                "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]"));
    }

    public void clickCheckoutButton() {
        scroll(panel(), ScrollDirection.DOWN, 0.9);
        scroll(panel(), ScrollDirection.DOWN, 0.9);
        checkoutButton().click();
    }

    public void clickContinueShoppingButton() {
        continueShoppingButton().click();
    }
}
