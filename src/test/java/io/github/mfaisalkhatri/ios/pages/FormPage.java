package io.github.mfaisalkhatri.ios.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.IOSDriverManager.getDriver;


public class FormPage {

    private WebElement switchBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("switch"));
    }

    public String getSwitchText() {
        return getDriver().findElement(AppiumBy.accessibilityId("switch-text")).getText();
    }

    private WebElement dropdownField() {
        return getDriver().findElement(AppiumBy.accessibilityId("text_input"));
    }

    private void selectDropdownValue(String option) {
        getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypePickerWheel[`value == \"Select an item...\"`]")).sendKeys(option);
    }

    public String getDropdownValue() {
        return dropdownField().getAttribute("value");
    }

    private WebElement doneBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("done_button"));
    }

    private WebElement activeBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("button-Active"));
    }

    public String getActiveMessageTitle() {
        return getDriver().findElement(AppiumBy.iOSClassChain("**/XCUIElementTypeStaticText[`label == \"This button is\"`]")).getText();
    }

    public String getActiveMessage() {
        return getDriver().findElement(AppiumBy.accessibilityId("This button is active")).getText();
    }

    private WebElement okBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("OK"));
    }

    private WebElement inActiveBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("button-Inactive"));
    }

    public void fillForm(String input, String option) {
        HomePage homePage = new HomePage();
        homePage.openMenu("Forms");
        inputField().sendKeys(input);
        switchBtn().click();
        dropdownField().click();
        selectDropdownValue(option);
        doneBtn().click();
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

    public String getInputText() {
        return getDriver().findElement(AppiumBy.accessibilityId("input-text-result")).getText();
    }

    private WebElement inputField() {
        return getDriver().findElement(AppiumBy.accessibilityId("text-input"));
    }


}
