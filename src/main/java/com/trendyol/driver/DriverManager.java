package com.trendyol.driver;

import com.trendyol.utils.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Objects;

import static com.trendyol.utils.Constants.*;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final PropertyReader propertyReader = new PropertyReader();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        String browser = propertyReader.propertyFile(DRIVER);

        if (Objects.isNull(driverPool.get())) {
            if (browser.equals(CHROME)) {
                getChromeDriver();
            } else if (browser.equals(FIREFOX)) {
                getFireFoxDriver();
            }
            else throw new IllegalArgumentException("Unknown browser " + browser);

            driverPool.get().get(propertyReader.propertyFile(BASE_URL));
            driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
            driverPool.get().manage().window().maximize();
        }
        return driverPool.get();
    }

    private static void getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driverPool.set(new ChromeDriver());
    }

    private static void getFireFoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        driverPool.set(new FirefoxDriver());
    }

    public static void tearDown() {
        if(!Objects.isNull(driverPool.get())){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
