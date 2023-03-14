package io.github.mfaisalkhatri.pages.wdio;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class FormPage {

    private WebElement switchBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("switch"));
    }

    public String getSwitchText() {
        return getDriver().findElement(AppiumBy.accessibilityId("switch-text")).getText();
    }

    private WebElement dropdownField() {
        return getDriver().findElement(AppiumBy.accessibilityId("Dropdown"));
    }

    private void selectDropdownValue(int option) {
        getDriver().findElements(AppiumBy.id("android:id/text1")).get(option).click();
    }

    public String getSelectedDropdownValue() {
        return getDriver().findElements(AppiumBy.className("android.widget.EditText")).get(1).getText();
    }

    private WebElement activeBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("button-Active"));
    }

    public String getActiveMessageTitle() {
        return getDriver().findElement(AppiumBy.id("android:id/alertTitle")).getText();
    }

    public String getActiveMessage() {
        return getDriver().findElement(AppiumBy.id("android:id/message")).getText();
    }

    private WebElement okBtn() {
        return getDriver().findElement(AppiumBy.id("android:id/button1"));
    }

    private WebElement inActiveBtn() {
        return getDriver().findElement(AppiumBy.accessibilityId("button-Inactive"));
    }

    public void fillForm(String input, int option) {
        inputField().sendKeys(input);
        switchBtn().click();
        dropdownField().click();
        selectDropdownValue(option);
    }

    public void submitForm() {
        activeBtn().click();
    }

    public String checkInActiveBtn() {
        return inActiveBtn().getAttribute("long-clickable");
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
