package io.github.mfaisalkhatri.android.pages.wdio;

import org.openqa.selenium.By;

import java.util.Set;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

/**
 * @author Faisal Khatri
 * @since 3/22/2023
 **/
public class WebViewPage {


    public void switchToWebView() {
        Set<String> contextNames = getDriver().getContextHandles();
        getDriver().context(contextNames.toArray()[1].toString());
        //getDriver().context("WEBVIEW_com.wdiodemoapp");
    }

    public void switchToNativeApp() {
        getDriver().context("NATIVE_APP");
    }

    public String getMainPageText() {
        HomePage homePage = new HomePage();
        homePage.openMenu("Webview");
        switchToWebView();
        return getDriver().findElement(By.cssSelector("header > div > p"))
                .getText();
    }
}
