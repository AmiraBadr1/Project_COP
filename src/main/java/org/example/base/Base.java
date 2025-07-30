package org.example.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Base {
        public static WebDriver driver;

    public static void lunchBrowser(String bro, String ul) {

        if (bro.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        } else if (bro.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            throw new Error("browser not supported");
        }
        driver.manage().window().maximize();
        driver.get(ul);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    }
