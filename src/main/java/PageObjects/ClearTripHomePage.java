package PageObjects;

import Utilities.GenUtils;
import Utilities.Wait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClearTripHomePage {

    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(ClearTripHomePage.class);

    public ClearTripHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);

    }

    public static String parentWindowHandle = "";
    @FindBy(xpath = "//h1[normalize-space()='Search flights']")
    private WebElement homePageSearchTitle;
    @FindBy(xpath = "//input[@placeholder='Where from?']")
    private WebElement whereFrom;

    @FindBy(xpath = "//p[contains(text(),'Bangalore')]")
    private WebElement whereFromOption;

    @FindBy(xpath = "//input[@placeholder='Where to?']")
    private WebElement whereTo;
    @FindBy(xpath = "//p[contains(text(),'Delhi')]")
    private WebElement wheretoOption;

    @FindBy(xpath = "(//div[contains(@class,'flex flex-middle p-relative homeCalender')]/button)[1]")
    private WebElement whereFromButton;

    @FindBy(xpath = "(//div[contains(@class,'flex flex-middle p-relative homeCalender')]/button)[2]")
    private WebElement whereToButton;

    @FindBy(xpath = "(//div[contains(@class,'DayPicker-wrapper')]/div/div/div/div/div)[31]")
    private WebElement whereFromCalendarDate;
    //div[contains(@aria-label,'Tue Dec 19 2023')]

    @FindBy(xpath = "(//div[contains(@class,'DayPicker-wrapper')]/div/div/div/div/div)[38]")
    private WebElement whereToCalendarDate;

    @FindBy(xpath = "//span[.='Search flights']")
    private WebElement searchFlightButton;


    public void selectWhereFrom() {
        logger.info("Started to select whereFrom");
        Wait.untilElementIsVisible(driver, whereFrom, 10);
        whereFrom.click();
        whereFrom.sendKeys("Bangalore");
        Wait.untilElementIsVisible(driver, whereFromOption, 5);
        whereFromOption.click();
        logger.info("where from option clicked");
        logger.info("Wherefrom Selection complete");
    }

    public void selectWhereTo() {
        logger.info("Started to select whereTo");
        Wait.untilElementIsVisible(driver, whereTo, 10);
        whereTo.click();
        whereTo.sendKeys("Delhi");
        Wait.untilElementIsVisible(driver, wheretoOption, 5);
        wheretoOption.click();
        logger.info("where to option clicked");
        logger.info("whereto complete");
    }

    public void selectWhereFromDate() throws InterruptedException {
        Thread.sleep(3000);
        GenUtils.scrollIntoView(driver, whereFromButton);
        //Wait.untilElementIsVisible(driver,whereFromButton,10);
        whereFromButton.click();
        logger.info("clicked where from calendar");
        Thread.sleep(3000);
        GenUtils.scrollIntoView(driver, whereFromCalendarDate);
        Wait.untilElementIsVisible(driver, whereFromCalendarDate, 10);
        Thread.sleep(3000);
        whereFromCalendarDate.click();
        logger.info("clicked exact where from date");

    }

    public void selectWhereToDate() throws InterruptedException {
        Thread.sleep(5000);
        Wait.untilElementIsVisible(driver, whereToButton, 10);
        GenUtils.scrollIntoView(driver, whereToButton);
        Thread.sleep(3000);
        whereToButton.click();
        Thread.sleep(3000);
        Wait.untilElementIsVisible(driver, whereToCalendarDate, 10);
        whereToCalendarDate.click();
        logger.info("clicked exact where to date");

    }

    public void pressSearchFlight() throws InterruptedException {
        Thread.sleep(3000);
        GenUtils.scrollIntoView(driver, searchFlightButton);
        Wait.untilElementIsVisible(driver, searchFlightButton, 7);
        parentWindowHandle = driver.getWindowHandle();
        System.out.println("The parent window Handle is : " + parentWindowHandle);
        searchFlightButton.click();
        Thread.sleep(15000);


    }
}
