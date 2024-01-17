package io.github.mfaisalkhatri.utils;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;

import static io.github.mfaisalkhatri.drivers.AndroidDriverManager.getDriver;

public class MobileTestUtils {
    public static void scroll(WebElement panel, ScrollDirection dir, double scrollRatio) {
        Point midPoint;
        Dimension size;

        if (scrollRatio < 0 || scrollRatio > 1) {
            throw new Error("Scroll distance must be between 0 and 1");
        }

        if (panel != null) {
            midPoint = getCenter(panel);
        } else {
            size = getDriver().manage().window().getSize();
            System.out.println(size);
            midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        }

        int bottom = midPoint.y + (int) (midPoint.y * scrollRatio);
        int top = midPoint.y - (int) (midPoint.y * scrollRatio);
        int left = midPoint.x - (int) (midPoint.x * scrollRatio);
        int right = midPoint.x + (int) (midPoint.x * scrollRatio);

        if (dir == ScrollDirection.UP) {
            swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), Duration.ofSeconds(5));
        } else if (dir == ScrollDirection.DOWN) {
            swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), Duration.ofSeconds(5));
        } else if (dir == ScrollDirection.LEFT) {
            swipe(new Point(left, midPoint.y), new Point(right, midPoint.y), Duration.ofSeconds(5));
        } else {
            swipe(new Point(right, midPoint.y), new Point(left, midPoint.y), Duration.ofSeconds(5));
        }
    }

    private static void swipe(Point start, Point end, Duration duration) {

        PointerInput input = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(input.createPointerMove(duration, PointerInput.Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(ImmutableList.of(swipe));
    }

    private static Point getCenter(WebElement el) {
        Point location = el.getLocation();
        Dimension size = el.getSize();
        return new Point(location.x + size.getWidth() / 2, location.y + size.getHeight() / 2);
    }

    public enum ScrollDirection {
        UP, DOWN, LEFT, RIGHT
    }
}