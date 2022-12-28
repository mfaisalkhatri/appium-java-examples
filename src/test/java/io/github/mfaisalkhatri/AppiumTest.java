package io.github.mfaisalkhatri;


import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.pages.HomePage;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AppiumTest extends BaseTest{

    @Test
    public void testHomePageTagLine () {
        HomePage homePage = new HomePage ();
        assertEquals(homePage.tagLine (),"Demo app for the appium-boilerplate");

    }
}
