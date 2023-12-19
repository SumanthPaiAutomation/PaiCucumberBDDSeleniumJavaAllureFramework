package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "StepDefinitions/WebStepDefinitions",
        plugin = {
                "pretty",
                "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
                //"html:com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/WebTest.html",
                //"json:target/cucumber-reports/CucumberTestReport.json",
                //"timeline:target/test-output-thread/"
    },
        tags = "@Web"

)
@Test
public class WebTestRunner extends AbstractTestNGCucumberTests {

//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("================ BEFORE WEB TEST SUITE ================");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("================ AFTER WEB TEST SUITE ================");
    }
}
