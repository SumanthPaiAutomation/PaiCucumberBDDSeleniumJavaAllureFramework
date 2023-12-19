package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Wait {

    private static void until(WebDriver webDriver, int timeOutInSeconds, Function<WebDriver, Boolean> waitCondition) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, TimeUnit.SECONDS.toSeconds(timeOutInSeconds));
        try {
            webDriverWait.until(waitCondition);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void untilAjaxCallIsDone(WebDriver webDriver, int timeOutInSeconds) {
        until(webDriver, timeOutInSeconds, (function) -> {
            Boolean isJqueryCallDone = (Boolean) ((JavascriptExecutor) webDriver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone) System.out.println("jQuery call is in progress");
            return isJqueryCallDone;
        });
    }

    public static void untilPageReadyState(WebDriver webDriver, int timeOutInSeconds) {
        until(webDriver, timeOutInSeconds, (function) -> {
            String isPageLoaded = String.valueOf(((JavascriptExecutor) webDriver).executeScript("return document.readyState"));
            if (isPageLoaded.equals("complete")) {
                return true;
            } else {
                System.out.println("Document is loading");
                return false;
            }
        });
    }

    public static void untilElementIsVisible(WebDriver webDriver, WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(webDriver, TimeUnit.SECONDS.toSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOf(webElement));
    }
    public static void untilElementIsClickAble(WebDriver webDriver, WebElement webElement, int timeOutInSeconds) {
        new WebDriverWait(webDriver, TimeUnit.SECONDS.toSeconds(timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void untilElementIsPresent(WebDriver webDriver, String xpath, int timeOutInSeconds) {
        new WebDriverWait(webDriver, TimeUnit.SECONDS.toSeconds(timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }
    public static void untilListElementIsVisible(WebDriver webDriver, List<WebElement> webElements, int timeOutInSeconds) {
        new WebDriverWait(webDriver, TimeUnit.SECONDS.toSeconds(timeOutInSeconds)).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }
}
