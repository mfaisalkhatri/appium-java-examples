package io.github.mfaisalkhatri.ios.tests;


import io.github.mfaisalkhatri.ios.pages.HomePage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class IOSTests extends BaseTest {

    @Test
    public void testHomePageTitle() {
        HomePage homePage = new HomePage();
        assertEquals(homePage.getTitle(), "WEBDRIVER");
        assertEquals(homePage.tagLine(), "Demo app for the appium-boilerplate");
    }

}
