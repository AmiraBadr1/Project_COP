package stepDefinitions;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;


public class Hooks {
    WebDriver driver;

    @After
    public void runAfterAnyScenario() {

        if (driver != null) {
            driver.quit();

            System.out.println("run after any scenario");
        }
    }
}

