package io.github.mfaisalkhatri;

import static io.github.mfaisalkhatri.drivers.DriverManager.createAndroidDriver;
import static io.github.mfaisalkhatri.drivers.DriverManager.quitSession;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * @author Faisal Khatri
 * @since 10/14/2022
 **/
public class BaseTest {

    @BeforeClass
    public void testSetup()  {
        createAndroidDriver();
    }

    @AfterClass
    public void tearDown() {
        quitSession ();
    }

}
