package io.github.mfaisalkhatri;


import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.pages.wdio.HomePage;
import io.github.mfaisalkhatri.pages.wdio.SignUpPage;
import org.testng.annotations.Test;

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
        HomePage homePage = new HomePage();
        homePage.openMenu("Login");

        SignUpPage signUpPage = new SignUpPage();
        signUpPage.openSignUpForm();
        signUpPage.signUp("test@email.com", "Pass@12345");
        assertEquals(signUpPage.getSuccessMessageTitle(), "Signed Up!");
        assertEquals(signUpPage.getSuccessMessage(), "You successfully signed up!");
    }

}
