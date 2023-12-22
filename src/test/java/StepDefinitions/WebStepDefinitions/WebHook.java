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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.ByteArrayInputStream;

public class WebHook {
    AllDriverManager allDriverManager;


    public WebHook(AllDriverManager allDriverManager) {
        this.allDriverManager = allDriverManager;
    }

    @Before
    public void setUp() {
        System.out.println("Any pre-requisites for test suite can be handled here");
    }

    @After
    public void tearDown(Scenario scenario) {

        if(scenario.isFailed()) {
            try {
                LocalDateTime currentDate = LocalDateTime.now();;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String formattedDateTime = currentDate.format(formatter);
                String fileName = "TestResult" + formattedDateTime;
                byte[] screenshot = ((TakesScreenshot)allDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment(fileName,new ByteArrayInputStream(screenshot));
            } catch (WebDriverException noSupportScreenshot) {
                System.err.println(noSupportScreenshot.getMessage());
            }
        }
        allDriverManager.closeDriver();
    }
}
