package StepDefinitions.WebStepDefinitions;

import Managers.FileReaderManager;
import Utilities.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.ByteArrayInputStream;

public class WebHook {

    TestContext testContext;
    WebDriver webDriver;

    public WebHook(TestContext context) {
        testContext = context;
    }

    @Before
    public void setUp() {
        webDriver = testContext.getDriverManager().getDriver();// for webdriver
        //webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());//comment this line for Mobile
    }

    @After
    public void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            try {

                //String screenshot=((TakesScreenshot)testContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot)testContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("WebTestFailedScreenShot",new ByteArrayInputStream(screenshot));
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        //testContext.getDriverManager().closeDriver();
    }
}
