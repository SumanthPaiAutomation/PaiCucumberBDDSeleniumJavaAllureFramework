package PageObjects;

import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class ClearTripCartPage {
    WebDriver driver;

    public ClearTripCartPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//h2[normalize-space()='Review your itinerary']")
    private WebElement itenaryText;

    public boolean verifyCartPage() {
        Set<String> windowHandles = driver.getWindowHandles();
        boolean status = false;
        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()) {
            String childWindow = iterator.next();
            if (!ClearTripHomePage.parentWindowHandle.equals(childWindow)) {
                driver.switchTo().window(childWindow);
                System.out.println("Number of child windows : "+windowHandles.size());
                System.out.println("Child window reached : "+childWindow);
                Wait.untilElementIsVisible(driver, itenaryText, 10);
                if (itenaryText.isDisplayed()) {
                    status = true;
                } else {
                    status = false;
                }
                driver.close();
            }
        }
        return status;

    }

    public void switchToTicketList() {
        driver.switchTo().window(ClearTripHomePage.parentWindowHandle);
        driver.quit();
    }

}
