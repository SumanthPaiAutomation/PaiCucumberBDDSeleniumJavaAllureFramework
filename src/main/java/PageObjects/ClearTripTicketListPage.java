package PageObjects;

import Utilities.Wait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClearTripTicketListPage {
    private final WebDriver driver;

    public ClearTripTicketListPage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//button[contains(.,'Book now')]")
    private WebElement BookNowButton;

    public boolean reachedTicketListPage() {
        String curUrl = driver.getCurrentUrl();
        if (curUrl.contains("results")) {
            return true;
        } else {
            return false;
        }
    }

    public void clickOnBookNow() {
        Wait.untilElementIsVisible(driver, BookNowButton, 6);
        BookNowButton.click();
    }
}
