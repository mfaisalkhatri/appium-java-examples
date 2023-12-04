package io.github.mfaisalkhatri.android.pages.wdio;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class SwipePage {


    public SwipePage() {
        final HomePage homePage = new HomePage();
        homePage.openMenu("Swipe");
    }

    public void performHorizontalSwipe() {

        final WebElement sourceElement = getDriver().findElement(AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[1]"));

        final Point source = sourceElement.getLocation();
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final Sequence swipe = new Sequence(finger, 1);
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
        final var screenSize = getDriver()
                .manage()
                .window()
                .getSize();
        final var xCenter = screenSize.width / 2;
        final var yCenter = screenSize.height / 2;
        final var center = new Point(xCenter, yCenter);

        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        final Sequence swipe = new Sequence(finger, 1);
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
        final WebElement targetElement = getDriver().findElement(AppiumBy.androidUIAutomator
                ("new UiScrollable(new UiSelector()" +
                        ".scrollable(true)).scrollIntoView(new UiSelector().text(\"You found me!!!\"))"));
        return targetElement.getText();
    }

    public String swipeTillElement() {
        final WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(1));

        WebElement targetElement = null;
        boolean found = false;
        while (!found) {
            try {
                targetElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.
                        androidUIAutomator("new UiSelector().text(\"You found me!!!\")")));
                found = true;
            } catch (final TimeoutException e) {
                performVerticalSwipe();
            }
        }
        return targetElement.getText();
    }
}
