package StepDefinitions.WebStepDefinitions;

import Managers.AllDriverManager;
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
    AllDriverManager allDriverManager;
    WebDriver webDriver;

    public WebHook(AllDriverManager allDriverManager) {
        this.allDriverManager = allDriverManager;
    }

    @Before
    public void setUp() {
        webDriver = allDriverManager.getDriver();// for webdriver
        //webDriver.get(FileReaderManager.getInstance().getConfigFileReader().getUrl());//comment this line for Mobile
    }

    @After
    public void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            try {

                //String screenshot=((TakesScreenshot)testContext.getDriverManager().getDriver()).getScreenshotAs(OutputType.BYTES);
                byte[] screenshot = ((TakesScreenshot)allDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("WebTestFailedScreenShot",new ByteArrayInputStream(screenshot));
                scenario.attach(screenshot, "image/png", "screenshot");
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        allDriverManager.closeDriver();
    }
}
