package io.github.mfaisalkhatri.ios.tests;


import io.github.mfaisalkhatri.ios.pages.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class IOSTests extends BaseTest {

    @Test
    public void testHomePageTitle() {
        HomePage homePage = new HomePage();
        assertEquals(homePage.getTitle(), "WEBDRIVER");
        assertEquals(homePage.tagLine(), "Demo app for the appium-boilerplate");
    }

    @Test
    public void testForm() {
        final String inputText = "This is Appium Test";
        String dropdownValue = "This app is awesome";
        FormPage formPage = new FormPage();
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

    @Test
    public void testSwipeOnElement() {
        SwipePage swipePage = new SwipePage();
        swipePage.performHorizontalSwipe();
        swipePage.performVerticalSwipe();

    }

    @Test
    public void testSwipeTillElement() {
        SwipePage swipePage = new SwipePage();
        assertEquals(swipePage.swipeTillElement(), "You found me!!!");
    }

    @Test
    public void testSwipeAndFindElement() {
        SwipePage swipePage = new SwipePage();
        assertEquals(swipePage.swipeAndFindElement(), "You found me!!!");

    }

    @Test
    public void testWebView() {
        WebViewPage webViewPage = new WebViewPage();

        assertEquals(webViewPage.getMainPageText(), "Next-gen browser and mobile automation test framework for Node.js");
        webViewPage.switchToNativeApp();
    }

    @Test
    public void testDragAndDrop() {
        DragPage dragPage = new DragPage();
        dragPage.dragAndDropPrices();
        assertEquals(dragPage.congratulationsText(), "Congratulations You made it, click retry if you want to try it again. Retry");
    }


}
