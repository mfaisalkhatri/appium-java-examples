package io.github.mfaisalkhatri.android.pages.swaglabs;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class CheckoutInformationPage {
    private WebElement firstNameField() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-First Name"));
    }

    private WebElement lastNameField() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Last Name"));
    }

    private WebElement postalCodeField() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-Zip/Postal Code"));
    }

    private WebElement continueButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-CONTINUE"));
    }

    private WebElement cancelButton() {
        return getDriver().findElement(AppiumBy.accessibilityId("test-CANCEL"));
    }

    public void enterFirstName(String firstName) {
        firstNameField().sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField().sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeField().sendKeys(postalCode);
    }

    public void clickContinueButton() {
        continueButton().click();
    }

    public void clickCancelButton() {
        cancelButton().click();
    }
}
