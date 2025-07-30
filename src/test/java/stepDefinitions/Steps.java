package stepDefinitions;

// ===== Old Tests Code: Before Applying Page Object Model (POM) =====

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Steps extends Base {

    Base base;
    @Given("User opens {string} browser {string}")
    public void user_opens_chrome_browser(String browser,String url) {
        lunchBrowser(browser,url);
    }
    @Then("the page title should be {string}")
    public void the_page_title_should_be(String unexpectedTitle) {

        String actualTitle = driver.getTitle();
        System.out.println("unexpectedTitle = " + unexpectedTitle);
        System.out.println("Title we got = " + actualTitle);

        boolean containsUnexpected = actualTitle.toLowerCase().contains(unexpectedTitle.toLowerCase());
        assertTrue("Title should not be "+ unexpectedTitle, containsUnexpected);

    }

    @Then("DuckDuckGo logo should be visible")
    public void DuckDuckGo_logo_should_be_visible(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div/main/article/div[1]/div[1]/div[2]/div/header/div/section[1]/a/img")));

        System.out.println(logo);
        assertTrue("DuckDuckGo logo should be visible", logo.isDisplayed());

    }
    @When ("User searches for {string}")
    public void User_searches_for(String searchWord){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchbox_input")));
        searchBox.sendKeys(searchWord + Keys.ENTER);
    }

    @Then("The first result link should be {string}")
    public void The_first_result_link_should_be(String expectedHref){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waitLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("eVNpHGjtxRBq_gLOfGDr")));

        List<WebElement> elements = driver.findElements(By.className("eVNpHGjtxRBq_gLOfGDr"));

        WebElement firstLink = elements.get(0);
        String actualHref = firstLink.getAttribute("href");
        assertEquals("First result URL is incorrect", expectedHref, actualHref);

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

        WebElement fourthText = elements.get(3);
        String actualText = fourthText.getAttribute("text");
        assertEquals("fourth result text is correct", expectedWord, actualText);


    }
    @And("User goes to the second page of results")
    public void User_goes_to_the_second_page_of_results(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waitLink = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("eVNpHGjtxRBq_gLOfGDr")));

        List<WebElement> elements = driver.findElements(By.className("eVNpHGjtxRBq_gLOfGDr"));

        WebElement secondLink = elements.get(1);
        secondLink.click();
    }
    @Then("the page link should be {string}")
    public void the_page_link_should_be(String expectedLink) {

        String actualLink = driver.getCurrentUrl();

        System.out.println("Expected Link = " + expectedLink);
        System.out.println("Actual URL = " + actualLink);

        boolean containsExpected = actualLink.toLowerCase().contains(expectedLink.toLowerCase());

        assertTrue(" The page URL does NOT contain the expected link", containsExpected);
        System.out.println("The page URL is correct: " + actualLink);

    }
    @When("User checks the first checkbox if not already checked")
    public void User_checks_the_first_checkbox_if_not_already_checked(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkboxes\"]/input[1]")));

        firstCheckbox.click();
    }
    @Then("Both checkboxes should be checked")
    public void both_checkboxes_should_be_checked() {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        for (int i = 0; i < checkboxes.size(); i++) {
            boolean isChecked = checkboxes.get(i).isSelected();
            System.out.println("Checkbox " + (i + 1) + " is selected: " + isChecked);
            assertTrue("Checkbox " + (i + 1) + " should be selected", isChecked);
        }

    }
    @Then("The country for the company {string} should be {string}")
    public void the_country_for_the_company_should_be(String company, String expectedCountry) {

        WebElement table = driver.findElement(By.id("customers"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        boolean companyFound = false;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() > 0) {
                System.out.println("Found row: " + cells.get(0).getText());
            }

            if (cells.size() > 0 && cells.get(0).getText().equalsIgnoreCase(company)) {
                String actualCountry = cells.get(2).getText();
                System.out.println("Company: " + company + " | Actual Country: " + actualCountry);
                assertEquals("Country mismatch", expectedCountry, actualCountry);
                companyFound = true;
                break;
            }
        }
        if (!companyFound) {
            System.out.println("Company " + company + " not found in table");
        }

        assertTrue("Company not found in table", companyFound);

    }
    @When("User uploads the file {string}")
    public void user_uploads_the_file(String imagePath ) {
        WebElement uploadInput = driver.findElement(By.id("file-upload"));
        uploadInput.sendKeys(imagePath);

        WebElement uploadBtn = driver.findElement(By.id("file-submit"));
        uploadBtn.click();
    }
    @Then("The file should be uploaded successfully")
    public void the_file_should_be_uploaded_successfully() {
        WebElement uploadedHeader = driver.findElement(By.tagName("h3"));
        String actualText = uploadedHeader.getText();

        assertEquals("File upload failed", "File Uploaded!", actualText);

    }
    @When("User drags the draggable element and drops it into the target")
    public void user_drags_and_drops_element() {

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, target).perform();
    }
    @Then("The drop area text should be {string}")
    public void the_drop_area_text_should_be(String expectedText) {

        WebElement target = driver.findElement(By.id("droppable"));
        String actualText = target.getText();

        System.out.println("Actual text after drop: " + actualText);
        assertEquals("Drop result text does not match", expectedText, actualText);

    }

}
