package io.github.mfaisalkhatri;

import static io.github.mfaisalkhatri.drivers.DriverManager.createAndroidDriver;
import static io.github.mfaisalkhatri.drivers.DriverManager.quitSession;

import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 10/13/2022
 **/
public class AppiumTest {

    @Test
    public void appiumServerTest () {
        createAndroidDriver();
        System.out.println ("Server and Android Driver started successsfully!!");
        quitSession ();
    }
}
