package stepDefinitions;

import io.cucumber.java.After;
import org.example.base.Base;

public class Hooks extends Base {

    @After
    public void quitBrowser() {
            driver.quit();
        }

}
