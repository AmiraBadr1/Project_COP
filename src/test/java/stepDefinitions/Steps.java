package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Steps {

    WebDriver driver;
    @Given("User opens chrome browser {string}")
    @Given("User navigates to upload page {string}")
    public void user_opens_chrome_browser(String url) {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        System.out.println("driver = " + driver);

    }


    @Given("User opens firefox browser {string}")
    public void user_opens_firefox_browser(String fireUrl) {

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(fireUrl);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        System.out.println("driver = " + driver);

    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String unexpectedTitle) {

        String actualTitle = driver.getTitle();
        System.out.println("unexpectedTitle = " + unexpectedTitle);
        System.out.println("Title we got = " + actualTitle);

        boolean containsUnexpected = actualTitle.toLowerCase().contains(unexpectedTitle.toLowerCase());
        System.out.println("containsUnexpected? " + containsUnexpected);

        assertTrue("Title should not be "+ unexpectedTitle, containsUnexpected);
        driver.quit();
    }


    @Then("DuckDuckGo logo should be visible")
    public void DuckDuckGo_logo_should_be_visible(){

        WebElement logo = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/main/article/div[1]/div[1]/div[2]/div/header/div/section[1]/a/img")));

        System.out.println(logo);
        assertTrue("DuckDuckGo logo should be visible", logo.isDisplayed());
        driver.quit();
    }

    @When ("User searches for {string}")
    public void User_searches_for(String searchWord){
        WebElement searchBox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("searchbox_input")));
        searchBox.sendKeys(searchWord + Keys.ENTER);

    }


    @Then("The first result link should be {string}")
    public void The_first_result_link_should_be(String expectedHref){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waitLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("eVNpHGjtxRBq_gLOfGDr")));

        List<WebElement> elements = driver.findElements(By.className("eVNpHGjtxRBq_gLOfGDr"));

        for (WebElement element : elements) {
            System.out.println(element.getAttribute("href"));

        }
        WebElement firstLink = elements.get(0);
        String actualHref = firstLink.getAttribute("href");
        Assert.assertEquals("First result URL is correct!", expectedHref, actualHref);
        driver.quit();

    }

    @Then("The text of the fourth result should be {string}")
    public void Then_The_text_of_the_third_result_should_be(String expectedWord){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waitLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("eVNpHGjtxRBq_gLOfGDr")));

        List<WebElement> elements = driver.findElements(By.className("eVNpHGjtxRBq_gLOfGDr"));

        for (WebElement element : elements) {
            System.out.println(element.getAttribute("text"));

        }
        WebElement fourthText = elements.get(2);
        String actualText = fourthText.getAttribute("text");
        Assert.assertEquals("fourth result text is correct!", expectedWord, actualText);

        driver.quit();

    }

    @And("User goes to the second page of results")
    public void User_goes_to_the_second_page_of_results(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waitLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("eVNpHGjtxRBq_gLOfGDr")));

        List<WebElement> elements = driver.findElements(By.className("eVNpHGjtxRBq_gLOfGDr"));

        for (WebElement element : elements) {
            System.out.println(element.getAttribute("href"));

        }
        WebElement secondLink = elements.get(1);
        secondLink.click();

    }

    @Then("the page link should be {string}")
    public void the_page_link_should_be(String expectedLink) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println("driver = " + driver);
        String actualLink = driver.getCurrentUrl();

        System.out.println("Expected Link = " + expectedLink);
        System.out.println("Actual URL     = " + actualLink);


        boolean containsExpected = actualLink.toLowerCase().contains(expectedLink.toLowerCase());

        try {
            assertTrue(" The page URL does NOT contain the expected link!", containsExpected);
            System.out.println("The page URL is correct: " + actualLink);
        } catch (AssertionError e) {
            System.out.println("Assertion failed: " + e.getMessage());
            throw e;
        } finally {
            driver.quit();
            System.out.println("Driver closed.");
        }
    }

    @When("User checks the first checkbox if not already checked")
    public void User_checks_the_first_checkbox_if_not_already_checked(){
        WebElement firstCheckbox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkboxes\"]/input[1]")));
        firstCheckbox.click();
    }

    @Then("Both checkboxes should be checked")
    public void both_checkboxes_should_be_checked() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for (int i = 0; i < checkboxes.size(); i++) {
            boolean isChecked = checkboxes.get(i).isSelected();
            System.out.println("Checkbox " + (i + 1) + " is selected: " + isChecked);
            Assert.assertTrue("Checkbox " + (i + 1) + " should be selected", isChecked);
        }
        driver.quit();

    }

    @Then("The country for the company {string} should be {string}")
    public void the_country_for_the_company_should_be(String company, String expectedCountry) {
        System.out.println("[STEP] Locating the table...");
        WebElement table = driver.findElement(By.id("customers"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        boolean companyFound = false;

        System.out.println("[STEP] Looping through table rows...");
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 0) {
                System.out.println("[INFO] Found row: " + cells.get(0).getText());
            }

            if (cells.size() > 0 && cells.get(0).getText().equalsIgnoreCase(company)) {
                String actualCountry = cells.get(2).getText();
                System.out.println("[MATCH] Company: " + company + " | Actual Country: " + actualCountry);
                Assert.assertEquals("Country mismatch!", expectedCountry, actualCountry);
                companyFound = true;
                break;
            }
        }

        if (!companyFound) {
            System.out.println("[ERROR] Company '" + company + "' not found in table!");
        }

        Assert.assertTrue("Company not found in table!", companyFound);
        System.out.println("[STEP] Assertion passed. Quitting browser.");
        driver.quit();
    }

    @When("User uploads the file {string}")
    public void user_uploads_the_file(String filePath) {
        System.out.println("[STEP] Uploading file: " + filePath);

        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys(filePath);

        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        uploadBtn.click();
        System.out.println("[STEP] Clicked on upload button.");
    }

    @Then("The file should be uploaded successfully")
    public void the_file_should_be_uploaded_successfully() {
        WebElement uploadedHeader = driver.findElement(By.tagName("h3"));
        String actualText = uploadedHeader.getText();
        System.out.println("[STEP] Uploaded header text: " + actualText);

        Assert.assertEquals("File upload failed!", "File Uploaded!", actualText);
        driver.quit();

    }


    @When("User drags the draggable element and drops it into the target")
    public void user_drags_and_drops_element() {

            WebElement source = driver.findElement(By.id("draggable"));
            WebElement target = driver.findElement(By.id("droppable"));

            Actions actions = new Actions(driver);
            actions.dragAndDrop(source, target).perform();

            System.out.println("Drag and drop action performed");

    }

    @Then("The drop area text should be {string}")
    public void the_drop_area_text_should_be(String expectedText) {
        WebElement target = driver.findElement(By.id("droppable"));
        String actualText = target.getText();

        System.out.println("Actual text after drop: " + actualText);
        Assert.assertEquals("Drop result text does not match", expectedText, actualText);
        driver.quit();

    }
}





