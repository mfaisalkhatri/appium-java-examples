package io.github.mfaisalkhatri.ios.paralleltests.tests;

import io.appium.java_client.ios.IOSDriver;
import io.github.mfaisalkhatri.ios.paralleltests.pages.FormPage;

import static org.testng.Assert.assertEquals;

public class BaseTest {


    public void testForm(final IOSDriver iosDriver) {

        final String inputText = "This is Appium Test";
        final String dropdownValue = "This app is awesome";

        final FormPage formPage = new FormPage(iosDriver);
        formPage.fillForm(inputText, dropdownValue);
        assertEquals(formPage.getInputText(), inputText);
        assertEquals(formPage.getSwitchText(), "Click to turn the switch OFF");
        assertEquals(formPage.getDropdownValue(), dropdownValue);
        formPage.submitForm();

        assertEquals(formPage.getActiveMessageTitle(), "This button is");
        assertEquals(formPage.getActiveMessage(), "This button is active");
        formPage.closeMessage();

        assertEquals(formPage.checkInActiveBtn(), "false");
    }

}
