package io.github.mfaisalkhatri;


import io.github.mfaisalkhatri.pages.wdio.FormPage;
import io.github.mfaisalkhatri.pages.wdio.HomePage;
import io.github.mfaisalkhatri.pages.wdio.SignUpPage;
import io.github.mfaisalkhatri.pages.wdio.SwipePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AppiumTest extends BaseTest {

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
}