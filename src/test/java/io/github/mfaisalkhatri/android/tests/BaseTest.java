package io.github.mfaisalkhatri.android.tests;

import io.github.mfaisalkhatri.logging.Log;
import io.github.mfaisalkhatri.utils.MobileTestUtils;
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
        Log.info("Setting up the android driver - calling createAndroidDriver() method!");
        createAndroidDriver();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Log.info("Quitting and removing the android driver - calling quitSession() method!");
        quitSession();
    }

}
