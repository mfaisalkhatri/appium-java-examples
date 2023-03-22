package io.github.mfaisalkhatri.pages.wdio;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

import java.util.Set;

import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 3/22/2023
 **/
public class WebViewPage {


    public void switchToWebView() {
        Set<String> contextNames = getDriver ().getContextHandles();
        getDriver ().context("WEBVIEW_com.wdiodemoapp");
    }

    public void switchToNativeApp() {
        getDriver ().context("NATIVE_APP");
    }

    public String getMainPageText () {
        HomePage homePage = new HomePage ();
        homePage.openMenu ("Webview");
        switchToWebView ();
        return getDriver ().findElement (By.cssSelector ("header > div > p"))
            .getText ();
    }
}
