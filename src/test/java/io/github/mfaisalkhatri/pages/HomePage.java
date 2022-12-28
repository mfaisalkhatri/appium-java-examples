package io.github.mfaisalkhatri.pages;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import io.appium.java_client.AppiumBy;

/**
 * @author Faisal Khatri
 * @since 11/30/2022
 **/
public class HomePage {

    public String tagLine () {
        return getDriver ().findElement (
                AppiumBy.androidUIAutomator ("new UiSelector().text(\"Demo app for the appium-boilerplate\")"))
            .getText ();
    }

}
