package Managers;
import Enums.DriverType;
import Enums.EnvironmentType;
import Managers.FileReaderManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AllDriverManager {

    public WebDriver webDriver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;

    public AllDriverManager() {
        driverType = FileReaderManager.getInstance().getConfigFileReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigFileReader().getEnvironment();
    }

    public WebDriver createLocalDriver() {
        switch (driverType) {
            case CHROME:
                WebDriverManager.chromedriver().clearDriverCache().setup();
//                ChromeOptions chromeOptions = new ChromeOptions();
//                chromeOptions.addArguments("--headless", "--window-size=1644,868");
                webDriver = new ChromeDriver();
                //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return webDriver;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--headless");
//                webDriver = new FirefoxDriver(firefoxOptions);
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return webDriver;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return webDriver;
            case SAFARI:
                webDriver = new SafariDriver();
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                return webDriver;
            default:
                throw new IllegalArgumentException("Unsupported driver type: " + driverType);
        }

    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("Provision to implement remote driver here");
    }

    private WebDriver createDriver() {
        switch (environmentType) {
            case LOCAL:
                webDriver = createLocalDriver();
                break;
            case REMOTE:
                webDriver = createRemoteDriver();
                break;

            default:
                throw new IllegalArgumentException("Unsupported environment type: " + environmentType);
        }
        return webDriver;
    }


    public WebDriver getDriver() {
        if (webDriver == null) {
            webDriver = createDriver();
        }
        return webDriver;
    }


    public void closeDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public AllDriverManager getAllDriverInstance(){
        return new AllDriverManager();
    }

}
