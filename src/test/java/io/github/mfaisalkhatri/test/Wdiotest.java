package io.github.mfaisalkhatri.test;

import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.BaseTest;
import io.github.mfaisalkhatri.pages.wdio.HomePage;
import io.github.mfaisalkhatri.pages.wdio.SignUpPage;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 2/23/2023
 **/
public class Wdiotest extends BaseTest {

    @Test
    public void testTitle () {
        HomePage homePage = new HomePage ();
        assertEquals (homePage.getTitle (), "WEBDRIVER");
    }

    @Test
    public void testSignUp () {
        SignUpPage signUpPage = new SignUpPage ();
        signUpPage.signUp ("test@email.com", "Pass12345");
        assertEquals (signUpPage.getSuccessMessageTitle (), "Signed Up!");
        assertEquals (signUpPage.getSuccessMessage (), "You successfully signed up!");
    }
}
