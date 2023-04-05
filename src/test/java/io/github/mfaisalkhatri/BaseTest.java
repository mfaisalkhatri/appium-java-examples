package io.github.mfaisalkhatri;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.createAndroidDriver;
import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.quitSession;

/**
 * @author Faisal Khatri
 * @since 10/14/2022
 **/
public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void testSetup() {
        createAndroidDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        quitSession();
    }

}
