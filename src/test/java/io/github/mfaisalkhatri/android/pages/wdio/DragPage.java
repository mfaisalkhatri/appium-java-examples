package io.github.mfaisalkhatri.android.pages.wdio;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;

public class DragPage {

    private WebElement dragLeft1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-l1"));
    }

    private WebElement dropLeft1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-l1"));
    }

    private WebElement dragLeft2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-l2"));
    }

    private WebElement dropLeft2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-l2"));
    }

    private WebElement dragLeft3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-l3"));
    }

    private WebElement dropLeft3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-l3"));
    }

    private WebElement dragRight1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-r1"));
    }

    private WebElement dropRight1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-r1"));
    }

    private WebElement dragRight2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-r2"));
    }

    private WebElement dropRight2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-r2"));
    }

    private WebElement dragRight3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-r3"));
    }

    private WebElement dropRight3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-r3"));
    }

    private WebElement dragCenter1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-c1"));
    }

    private WebElement dropCenter1() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-c1"));
    }

    private WebElement dragCenter2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-c2"));
    }

    private WebElement dropCenter2() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-c2"));
    }

    private WebElement dragCenter3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drag-c3"));
    }

    private WebElement dropCenter3() {
        return getDriver().findElement(AppiumBy.accessibilityId("drop-c3"));
    }


    private Point getElementCenter(WebElement element) {
        var location = element.getLocation();
        var size = element.getSize();

        return new Point(location.getX() + (size.getWidth() / 2), location.getY() + (size.getHeight() / 2));

    }

    private void dragAndDrop(WebElement dragMe, WebElement dropTo) {
        Point source = getElementCenter(dragMe);
        Point target = getElementCenter(dropTo);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence dragAndDrop = new Sequence(finger, 1);
        dragAndDrop.addAction(finger.createPointerMove(ofMillis(0), PointerInput.Origin.viewport(), source.x, source.y));
        dragAndDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        dragAndDrop.addAction(new Pause(finger, ofMillis(600)));
        dragAndDrop.addAction(finger.createPointerMove(ofMillis(600),
                PointerInput.Origin.viewport(),
                target.x, target.y));
        dragAndDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(singletonList(dragAndDrop));
    }

    public void dragAndDropPrices() {
        HomePage homePage = new HomePage();
        homePage.openMenu("Drag");
        dragAndDrop(dragLeft1(), dropLeft1());
        dragAndDrop(dragLeft2(), dropLeft2());
        dragAndDrop(dragLeft3(), dropLeft3());
        dragAndDrop(dragRight1(), dropRight1());
        dragAndDrop(dragRight2(), dropRight2());
        dragAndDrop(dragRight3(), dropRight3());
        dragAndDrop(dragCenter1(), dropCenter1());
        dragAndDrop(dragCenter2(), dropCenter2());
        dragAndDrop(dragCenter3(), dropCenter3());
    }

    public String congratulationsText() {
        return getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Congratulations\")")).getText();
    }

}
