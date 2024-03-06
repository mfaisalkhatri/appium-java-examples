package io.github.mfaisalkhatri.ios.paralleltests.demo;

import io.github.mfaisalkhatri.ios.paralleltests.pages.FormPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IOSParallelTests extends BaseTest {

    @Test
    public void testForm() {

        final String inputText = "This is Appium Test";
        final String dropdownValue = "This app is awesome";

        final FormPage formPage = new FormPage(getIOSDriver());
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
