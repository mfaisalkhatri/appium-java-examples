package io.github.mfaisalkhatri.android.pages.browser;

import org.openqa.selenium.By;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class TheInternetPage {

    public void navigateToInternetWebsite() {
        getDriver().navigate().to("https://the-internet.herokuapp.com/");
    }

    public String getPageHeader() {
        return getDriver().findElement(By.tagName("h1")).getText();
    }

}
