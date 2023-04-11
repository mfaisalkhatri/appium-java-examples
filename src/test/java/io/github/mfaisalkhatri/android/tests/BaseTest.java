package io.github.mfaisalkhatri.android.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.createAndroidDriver;
import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.quitSession;

/**
 * @author Faisal Khatri
 * @since 10/14/2022
 **/
public class BaseTest {

    @BeforeClass(alwaysRun = true)
    @Parameters("platform")
    public void testSetup() {
        createAndroidDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        quitSession();
    }

}
