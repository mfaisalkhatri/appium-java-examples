package io.github.mfaisalkhatri;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import io.github.mfaisalkhatri.pages.wdio.FormPage;
import io.github.mfaisalkhatri.pages.wdio.HomePage;
import io.github.mfaisalkhatri.pages.wdio.SignUpPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AppiumTest extends BaseTest {

    private HomePage homePage;
    @BeforeClass
    public void setup() {
        homePage = new HomePage();
    }

    @Test
    public void testHomePageTitle() {
        assertEquals(homePage.getTitle(), "WEBDRIVER");
        assertEquals(homePage.tagLine(), "Demo app for the appium-boilerplate");
    }

    @Test
    public void testSignUp() {
        homePage.openMenu("Login");

        SignUpPage signUpPage = new SignUpPage();
        signUpPage.openSignUpForm();
        signUpPage.signUp("test@email.com", "Pass@12345");
        assertEquals(signUpPage.getSuccessMessageTitle(), "Signed Up!");
        assertEquals(signUpPage.getSuccessMessage(), "You successfully signed up!");
        signUpPage.closeSuccessMessage();
    }

    @Test
    public void testForm() {
        homePage.openMenu("Forms");

        String inputText = "This is Appium Test";
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

}
