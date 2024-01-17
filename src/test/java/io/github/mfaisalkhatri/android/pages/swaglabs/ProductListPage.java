package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import io.github.mfaisalkhatri.utils.MobileTestUtils;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class ProductListPage extends MobileTestUtils {
    private WebElement productAddToCart() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-ADD TO CART"));
    }

    private WebElement filterButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Modal Selector Button"));
    }

    private WebElement filterPopUpOption() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Price (low to high)\"]"));
    }

    private WebElement toggleViewButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Toggle"));
    }

    private String firstItemPricePostToggleAndFilter() {
        return getDriver().findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"test-Price\" and @text=\"$7.99\"]")).getText();
    }

    private WebElement firstItemPostToggleAndFilter() {
        return getDriver().findElement(AppiumBy.xpath(
                "(//android.view.ViewGroup[@content-desc=\"test-Item\"])[1]/android.view.ViewGroup/android.widget.ImageView"));
    }

    private WebElement menuButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Menu"));
    }

    private WebElement cartButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Cart"));
    }

    private WebElement panel() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-PRODUCTS"));
    }

    public void addProducts() {
        productAddToCart().click();
        productAddToCart().click();
        scroll(panel(), ScrollDirection.DOWN, 0.5);
        productAddToCart().click();
        productAddToCart().click();
    }

    public void filterProducts() {
        filterButton().click();
        filterPopUpOption().click();
    }

    public String tapToggleButton() {
        toggleViewButton().click();
        return firstItemPricePostToggleAndFilter();
    }

    public boolean menuButtonClickable() {
        return menuButton().isEnabled();
    }

    public void clickFirstItemPostFilterAndToggle() {
        firstItemPostToggleAndFilter().click();
    }

    public void clickCartButton() {
        cartButton().click();
    }

}
