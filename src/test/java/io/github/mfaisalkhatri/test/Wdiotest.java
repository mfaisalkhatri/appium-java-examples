package io.github.mfaisalkhatri.test;

import static org.testng.Assert.assertEquals;

import io.github.mfaisalkhatri.BaseTest;
import io.github.mfaisalkhatri.pages.wdio.HomePage;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 2/23/2023
 **/
public class Wdiotest extends BaseTest {

    @Test
    public void testTitle() {
        HomePage homePage = new HomePage ();
        assertEquals (homePage.getTitle (),"WEBDRIVER");
    }
}
