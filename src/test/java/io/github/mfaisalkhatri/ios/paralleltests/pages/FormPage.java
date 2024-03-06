package io.github.mfaisalkhatri.ios.paralleltests.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;


public class FormPage {

    private final IOSDriver iosDriver;

    public FormPage(final IOSDriver iosDriver) {
        this.iosDriver = iosDriver;
    }

    private WebElement switchBtn() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("switch"));
    }

    public String getSwitchText() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("switch-text")).getText();
    }

    private WebElement dropdownField() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("text_input"));
    }

    private void selectDropdownValue(final String option) {
        this.iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`value == \"Select an item...\"`]")).sendKeys(option);
    }

    public String getDropdownValue() {
        return dropdownField().getAttribute("value");
    }

    private WebElement doneBtn() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("done_button"));
    }

    private WebElement activeBtn() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("button-Active"));
    }

    public String getActiveMessageTitle() {
        return this.iosDriver.findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"This button is\"`]")).getText();
    }

    public String getActiveMessage() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("This button is active")).getText();
    }

    private WebElement okBtn() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("OK"));
    }

    private WebElement inActiveBtn() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("button-Inactive"));
    }

    public void fillForm(final String input, final String option) {
        final HomePage homePage = new HomePage(this.iosDriver);
        homePage.openMenu("Forms");
        inputField().sendKeys(input);
        switchBtn().click();
        dropdownField().click();
        selectDropdownValue(option);
        doneBtn().click();
    }

    public String getInputText() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("input-text-result")).getText();
    }

    private WebElement inputField() {
        return this.iosDriver.findElement(AppiumBy.accessibilityId("text-input"));
    }

    public void submitForm() {
        activeBtn().click();
    }

    public String checkInActiveBtn() {
        return inActiveBtn().getAttribute("accessible");
    }

    public void closeMessage() {
        okBtn().click();
    }


}
