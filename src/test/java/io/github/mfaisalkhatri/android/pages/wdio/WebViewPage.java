package io.github.mfaisalkhatri.android.pages.wdio;

import io.github.mfaisalkhatri.drivers.AndroidDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

/**
 * @author Faisal Khatri
 * @since 3/22/2023
 **/
public class WebViewPage {


    public void switchToWebView() {//
        Set<String> contextNames = getDriver().getContextHandles();
        WebDriverWait wait = new WebDriverWait(AndroidDriverManager.getDriver(), Duration.ofSeconds(20));
        
        wait.until(d -> contextNames.size() > 1);
        //getDriver().context("WEBVIEW");
        getDriver().context(contextNames.toArray()[1].toString());
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
