package StepDefinitions.WebStepDefinitions;

import Managers.AllDriverManager;
import PageObjects.ClearTripCartPage;
import PageObjects.ClearTripHomePage;
import PageObjects.ClearTripTicketListPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheapestTicketSteps {

    AllDriverManager allDriverManager;
    WebDriver driver;
    ClearTripHomePage clearTripHomePage;
    ClearTripTicketListPage clearTripTicketListPage;
    ClearTripCartPage clearTripCartPage;

    public CheapestTicketSteps(AllDriverManager allDriverManager) {
        this.allDriverManager = allDriverManager;
        this.driver = allDriverManager.getDriver();
        this.clearTripHomePage = new ClearTripHomePage(driver);
        this.clearTripTicketListPage = new ClearTripTicketListPage(driver);
        this.clearTripCartPage = new ClearTripCartPage(driver);
    }


    @Given("I have the url of the booking website")
    public void iHaveTheUrlOfTheBookingWebsite() {
    }

    @When("I navigate to cleartrip website")
    public void iNavigateToCleartripWebsite() throws InterruptedException {
        driver.get("https://www.cleartrip.com/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        driver.navigate().refresh();
    }

    @And("I click on from location and type from location")
    public void iClickOnFromLocationAndTypeFromLocation() {
        clearTripHomePage.selectWhereFrom();
    }

    @And("I click on to destination and type to destination")
    public void iClickOnToDestinationAndTypeToDestination() {
        clearTripHomePage.selectWhereTo();
    }

    @And("I click on start trip date")
    public void iClickOnStartTripDate() throws InterruptedException {
        clearTripHomePage.selectWhereFromDate();
    }

    @And("I click on return trip date")
    public void iClickOnReturnTripDate() throws InterruptedException {
        clearTripHomePage.selectWhereToDate();
    }

    @And("I click on search flight button")
    public void iClickOnSearchFlightButton() throws InterruptedException {
        clearTripHomePage.pressSearchFlight();
    }

    @Then("I verify if I am navigated to price list page")
    public void iVerifyIfIAmNavigatedToPriceListPage() {
        Assert.assertTrue(clearTripTicketListPage.reachedTicketListPage(), "The Control Not reached Ticket Results page");
    }

    @And("I select cheapest from price")
    public void iSelectCheapestFromPrice() {

    }

    @And("I select cheapest to price")
    public void iSelectCheapestToPrice() {

    }

    @And("I click on book now Button")
    public void iClickOnBookNowButton() {
        clearTripTicketListPage.clickOnBookNow();
    }

    @Then("I verify if I am navigated to cart page with total price")
    public void iVerifyIfIAmNavigatedToCartPageWithTotalPrice() {
        Assert.assertTrue(clearTripCartPage.verifyCartPage(), "Did not reach cart page");
        clearTripCartPage.switchToTicketList();

    }
}
