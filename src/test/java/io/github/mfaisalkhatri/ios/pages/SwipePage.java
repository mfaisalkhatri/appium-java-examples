package io.github.mfaisalkhatri.ios.pages;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.github.mfaisalkhatri.drivers.IOSDriverManager.getDriver;

public class SwipePage {


    public SwipePage() {
        HomePage homePage = new HomePage();
        homePage.openMenu("Swipe");
    }

    public void performHorizontalSwipe() {

        WebElement sourceElement = getDriver().findElement(AppiumBy.xpath("(//XCUIElementTypeOther[@name=\"card\"])[1]"));

        Point source = sourceElement.getLocation();
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), source.x - (source.x * 5), source.y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(600)));
        getDriver()
                .perform(List.of(swipe));

    }

    public void performVerticalSwipe() {
        var screenSize = getDriver()
                .manage()
                .window()
                .getSize();
        var xCenter = screenSize.width / 2;
        var yCenter = screenSize.height / 2;
        var center = new Point(xCenter, yCenter);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), center.x, center.y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(
                finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), center.x, center.y - (center.y * 80 / 100)));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(new Pause(finger, Duration.ofMillis(1000)));
        getDriver()
                .perform(List.of(swipe));

    }

    public String swipeAndFindElement() {
        JavascriptExecutor js = getDriver();
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        WebElement element = getDriver().findElement(AppiumBy.
                iOSClassChain("**/XCUIElementTypeStaticText[`label == \"You found me!!!\"`]"));
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scrollToElement", params);

        return element.getText();
    }

    public String swipeTillElement() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        WebElement targetElement = null;
        boolean found = false;
        while (!found) {
            try {
                targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.
                        iOSClassChain("**/XCUIElementTypeStaticText[`label == \"You found me!!!\"`]")));
                found = true;
            } catch (TimeoutException e) {
                performVerticalSwipe();
            }
        }
        return targetElement.getText();
    }
}
