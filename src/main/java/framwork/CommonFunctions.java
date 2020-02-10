package framwork;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class CommonFunctions {
    private MobileDriver driver;

    public CommonFunctions(MobileDriver driver) {
        this.driver = driver;
    }

    public void click(String element) {
        waitForElementByXpath(element);
        driver.findElementByXPath("//*[@text='" + element + "']").click();
    }

    public void typeById(String element, String text) {
        waitForElementById(element);
        driver.findElementById(element).sendKeys(text);
    }
    public List<MobileElement> getMobileElements(String element) {
        waitForElementByXpath(element);
        return driver.findElementsByXPath("//*[@text='" + element + "']");
    }

    public List<MobileElement> getMobileElementsById(String element) {
        waitForElementById(element);
        return driver.findElementsById(element);
    }

    public void waitForElementByXpath(String element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='" + element + "']")));
    }

    public void waitForElementById(String element) {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));
    }

    public boolean isDisplayedXpath(String element) {
        waitForElementByXpath(element);
        return driver.findElementByXPath("//*[@text='" + element + "']").isDisplayed();
    }

    public void swipeByElements (AndroidElement startElement, AndroidElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);

        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);

        new TouchAction(driver)
                .press(point(startX,startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }

    public boolean elementIsPresentById(String selector){
        return driver.findElements( By.id(selector) ).size() != 0;
    }

    public boolean elementIsPresentByXpath(String element){
        return driver.findElementsByXPath("//*[@text='" + element + "']").size() != 0;
    }


}
