package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class DriverFactory {

    // Creates the web driver instance according to given environment
    public static WebDriver getDriver() {

        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME": browser;

        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.ignoreZoomSettings();
                return new InternetExplorerDriver(ieOptions);
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();
	            ChromeOptions chromeOptions = new ChromeOptions();
	            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
	            }
	            return new ChromeDriver(chromeOptions);
        }

    }

}
