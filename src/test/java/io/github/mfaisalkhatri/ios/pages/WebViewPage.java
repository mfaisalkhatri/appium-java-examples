package io.github.mfaisalkhatri.ios.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static io.github.mfaisalkhatri.drivers.IOSDriverManager.getDriver;

/**
 * @author Faisal Khatri
 * @since 4/16/2023
 **/
public class WebViewPage {


    public void switchToWebView() {
        Set<String> contextNames = getDriver().getContextHandles();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(d -> contextNames.size() > 1);
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
