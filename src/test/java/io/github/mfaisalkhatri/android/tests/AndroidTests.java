package io.github.mfaisalkhatri.android.tests;

import io.github.mfaisalkhatri.android.pages.browser.TheInternetPage;
import io.github.mfaisalkhatri.android.pages.proverbial.Notifications;
import io.github.mfaisalkhatri.android.pages.wdio.*;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AndroidTests extends BaseTest {

    @Test
    public void testHomePageTitle() {
        HomePage homePage = new HomePage();
        assertEquals(homePage.getTitle(), "WEBDRIVER");
        assertEquals(homePage.tagLine(), "Demo app for the appium-boilerplate");
    }

    @Test
    public void testSignUp() {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.signUp("test@email.com", "Pass@12345");
        assertEquals(signUpPage.getSuccessMessageTitle(), "Signed Up!");
        assertEquals(signUpPage.getSuccessMessage(), "You successfully signed up!");
        signUpPage.closeSuccessMessage();
    }

    @Test
    public void testForm() {
        final String inputText = "This is Appium Test";
        FormPage formPage = new FormPage();
        formPage.fillForm(inputText, 2);
        assertEquals(formPage.getInputText(), inputText);
        assertEquals(formPage.getSwitchText(), "Click to turn the switch OFF");
        assertEquals(formPage.getSelectedDropdownValue(), "Appium is awesome");
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
    public void testSwipeUsingScrollIntoView() {
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
    public void testNotification() {
        Notifications notifications = new Notifications();
        assertTrue(notifications.checkNotificationIsDisplayed());
        notifications.openNotificationPanel();

        assertEquals(notifications.getAppNotificationTitle(), "Proverbial");
        assertEquals(notifications.getNotificationTitle(), "Test Notification");
        assertEquals(notifications.getNotificationText(), "Please enjoy this notification");
    }

    @Test
    public void testChrome() {
        TheInternetPage theInternetPage = new TheInternetPage();
        theInternetPage.navigateToInternetWebsite();
        assertEquals(theInternetPage.getPageHeader(), "Welcome to the-internet");
    }

    @Test
    public void testDragAndDrop() {
        DragPage dragPage = new DragPage();
        dragPage.dragAndDropPrices();
        assertEquals(dragPage.congratulationsText(), "Congratulations");
    }
}