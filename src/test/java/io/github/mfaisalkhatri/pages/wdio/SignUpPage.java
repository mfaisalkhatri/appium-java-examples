package io.github.mfaisalkhatri.pages.wdio;

import static io.github.mfaisalkhatri.drivers.DriverManager.getDriver;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

/**
 * @author Faisal Khatri
 * @since 2/25/2023
 **/
public class SignUpPage {

    private WebElement signUpLink () {
        return getDriver ().findElement (AppiumBy.accessibilityId ("button-sign-up-container"));
    }

    private WebElement emailField () {
        return getDriver ().findElement (AppiumBy.accessibilityId ("input-email"));
    }

    private WebElement passwordField () {
        return getDriver ().findElement (AppiumBy.accessibilityId ("input-password"));
    }

    private WebElement confirmPasswordField () {
        return getDriver ().findElement (AppiumBy.accessibilityId ("input-repeat-password"));
    }

    private WebElement signUpBtn () {
        return getDriver ().findElement (AppiumBy.accessibilityId ("button-SIGN UP"));
    }

    public String getSuccessMessageTitle () {
        return getDriver ().findElement (AppiumBy.id ("android:id/alertTitle")).getText ();
    }

    public String getSuccessMessage () {
        return getDriver ().findElement (AppiumBy.id ("android:id/message")).getText ();
    }

    public void signUp(String email, String password) {
        HomePage homePage = new HomePage ();
        homePage.openMenu ("Login");
        signUpLink ().click ();
        emailField ().sendKeys (email);
        passwordField ().sendKeys (password);
        confirmPasswordField ().sendKeys (password);
        signUpBtn ().click ();
    }
}
